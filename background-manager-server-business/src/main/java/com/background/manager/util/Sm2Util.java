package com.background.manager.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.TreeMap;

public class Sm2Util {
    /**
     * 超算平台给我的公钥
     */
    public static String publicKey;
    /**
     * 超算平台的私钥：方便测试
     */
    public static String privateKey;

    /**
     * 自己的公钥：需要给出去的
     */
    public static String myPublicKey;

    /**
     * 自己的私钥
     */
    public static String myPrivateKey;

    static{
        Security.addProvider(new BouncyCastleProvider());
        publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEM/qZv8rpwhFHDXxQj25Pvzj2HYFhvJSNITqBnRq+BHpHifyZdy2S6moqo9MHq2VYs0v06bxp4vEFtgxlWuD3KA==";
        privateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgZn2Ir0mRCrTpB8eN7oNqy1E+IZss4JBXX97uk9nSwBmgCgYIKoEcz1UBgi2hRANCAAQz+pm/yunCEUcNfFCPbk+/OPYdgWG8lI0hOoGdGr4EekeJ/Jl3LZLqaiqj0werZVizS/TpvGni8QW2DGVa4Pco";
        myPublicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE0NG/a7lSo3Gc/DkrpYcm6goH9jHHYPuKYmSDrhIUBQdSw19Q3vpbjK/sYM6lm36vxGSxOzjr2CIhOIgQusAZZA==";
        myPrivateKey = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgN0L9YT41CwTEQhAL6QYDe+IyHFxiZdrAQDCmCo4FwK+gCgYIKoEcz1UBgi2hRANCAATQ0b9ruVKjcZz8OSulhybqCgf2Mcdg+4piZIOuEhQFB1LDX1De+luMr+xgzqWbfq/EZLE7OOvYIiE4iBC6wBlk";
    }

    /**
     * 随机生成sm2的公钥私钥对
     * @return 公钥私钥对
     */
    public static KeyPair generateSm2KeyPair() {
        try {
            final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
            // 获取一个椭圆曲线类型的密钥对生成器
            final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            // 使用SM2的算法区域初始化密钥生成器
            kpg.initialize(sm2Spec, random);
            // 获取密钥对
            KeyPair keyPair = kpg.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param publicKeyStr 以BASE64表示的sm2公钥
     * @param data 待加密字符串， UTF-8编码
     * @return 以BASE64表示的加密结果
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     */
    public static String encrypt(String publicKeyStr, String data) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        BCECPublicKey publicKey = getPublicKey(Base64.decode(publicKeyStr));
        //通过公钥对象获取公钥的基本域参数。
        ECParameterSpec ecParameterSpec = publicKey.getParameters();
        ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                ecParameterSpec.getG(), ecParameterSpec.getN());
        //通过公钥值和公钥基本参数创建公钥参数对象
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(publicKey.getQ(), ecDomainParameters);
        //根据加密模式实例化SM2公钥加密引擎
        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
        //初始化加密引擎
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
        byte[] arrayOfBytes = null;
        try {
            //将明文字符串转换为指定编码的字节串
            byte[] in = data.getBytes("utf-8");
            //通过加密引擎对字节数串行加密
            arrayOfBytes = sm2Engine.processBlock(in, 0, in.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将加密后的字节串转换为BASE 64字符串
        return Base64.toBase64String(arrayOfBytes);
    }

    /**
     *
     * @param privateKeyStr 以BASE64表示的sm2私钥
     * @param cipherData 以BASE64表示的加密结果
     * @return 解密后字符串， UTF-8编码
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     */
    public static String decrypt(String privateKeyStr, String cipherData) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        BCECPrivateKey privateKey = getPrivateKey(Base64.decode(privateKeyStr));
        byte[] cipherDataByte = Base64.decode(cipherData);
        //通过私钥对象获取私钥的基本域参数。
        ECParameterSpec ecParameterSpec = privateKey.getParameters();
        ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                ecParameterSpec.getG(), ecParameterSpec.getN());
        //通过私钥值和私钥钥基本参数创建私钥参数对象
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(privateKey.getD(),
                ecDomainParameters);
        //通过解密模式创建解密引擎并初始化
        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
        sm2Engine.init(false, ecPrivateKeyParameters);
        String result = null;
        try {
            //通过解密引擎对密文字节串进行解密
            byte[] arrayOfBytes = sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length);
            //将解密后的字节串转换为utf8字符编码的字符串（需要与明文加密时字符串转换成字节串所指定的字符编码保持一致）
            result = new String(arrayOfBytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param privateKeyStr 以BASE64表示的sm2私钥
     * @param dataJson 待签名json数据
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     * @throws CryptoException
     */
    public static String sign(String privateKeyStr, JSONObject dataJson) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, CryptoException {
        BCECPrivateKey privateKey = getPrivateKey(Base64.decode(privateKeyStr));
        ECParameterSpec ecParameterSpec = privateKey.getParameters();
        ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                ecParameterSpec.getG(), ecParameterSpec.getN());
        //通过私钥值和私钥钥基本参数创建私钥参数对象
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(privateKey.getD(),
                ecDomainParameters);

        //创建签名实例
        SM2Signer sm2Signer = new SM2Signer();

        //初始化签名实例,带上ID,国密的要求,ID默认值:1234567812345678
        try {
            sm2Signer.init(true, new ParametersWithID(new ParametersWithRandom(ecPrivateKeyParameters, SecureRandom.getInstance("SHA1PRNG")), Strings.toByteArray("1234567812345678")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String content = getSignContent(dataJson);
        byte[] message = content.getBytes();
        sm2Signer.update(message, 0, message.length);
        //生成签名,签名分为两部分r和s,分别对应索引0和1的数组
        byte[] signBytes = sm2Signer.generateSignature();
        return Base64.toBase64String(signBytes);
    }

    /**
     *
     * @param publicKeyStr 以BASE64表示的sm2公钥
     * @param dataJson 含有签名的待验签json数据
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws InvalidKeySpecException
     */
    public static boolean verify(String publicKeyStr, JSONObject dataJson) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        BCECPublicKey publicKey = getPublicKey(Base64.decode(publicKeyStr));
        ECParameterSpec ecParameterSpec = publicKey.getParameters();
        ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
                ecParameterSpec.getG(), ecParameterSpec.getN());
        //通过公钥值和公钥基本参数创建公钥参数对象
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(publicKey.getQ(), ecDomainParameters);
        //创建签名实例
        SM2Signer sm2Signer = new SM2Signer();
        ParametersWithID parametersWithID = new ParametersWithID(ecPublicKeyParameters, Strings.toByteArray("1234567812345678"));
        sm2Signer.init(false, parametersWithID);
        String sign = (String) dataJson.remove("sign");
        String content = getSignContent(dataJson);
        byte[] message = content.getBytes();
        sm2Signer.update(message, 0, message.length);
        //验证签名结果
        return sm2Signer.verifySignature(Base64.decode(sign));
    }

    private static String getSignContent(JSONObject rawData){
        JSONObject data = new JSONObject(new TreeMap<>());
        rawData.forEach((k,v) -> data.put(k,v));
        StringBuffer sb = new StringBuffer();
        data.forEach((k,v)->{
            if (v != null && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        });
        String stringData = sb.toString();
        return stringData == null || stringData.isEmpty() ? "" : stringData.substring(0,stringData.length()-1);
    }

    private static BCECPrivateKey getPrivateKey(byte[] privateBytes) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec eks2 = new PKCS8EncodedKeySpec(privateBytes);
        KeyFactory kf= KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        return (BCECPrivateKey) kf.generatePrivate(eks2);
    }

    private static BCECPublicKey getPublicKey(byte[] publicBytes) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec eks = new X509EncodedKeySpec(publicBytes);
        KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        return (BCECPublicKey) kf.generatePublic(eks);
    }

    public static void main(String[] args) throws Exception {

//        KeyPair keyPair = generateSm2KeyPair();
//        System.out.println(Base64.toBase64String(keyPair.getPublic().getEncoded()));
//        System.out.println(Base64.toBase64String(keyPair.getPrivate().getEncoded()));
//        String publicKeyStr = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEM/qZv8rpwhFHDXxQj25Pvzj2HYFhvJSNITqBnRq+BHpHifyZdy2S6moqo9MHq2VYs0v06bxp4vEFtgxlWuD3KA==";
//        String privateKeyStr = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgZn2Ir0mRCrTpB8eN7oNqy1E+IZss4JBXX97uk9nSwBmgCgYIKoEcz1UBgi2hRANCAAQz+pm/yunCEUcNfFCPbk+/OPYdgWG8lI0hOoGdGr4EekeJ/Jl3LZLqaiqj0werZVizS/TpvGni8QW2DGVa4Pco";
//        String publicKeyStr2 = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE0NG/a7lSo3Gc/DkrpYcm6goH9jHHYPuKYmSDrhIUBQdSw19Q3vpbjK/sYM6lm36vxGSxOzjr2CIhOIgQusAZZA==";
//        String privateKeyStr2 = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgN0L9YT41CwTEQhAL6QYDe+IyHFxiZdrAQDCmCo4FwK+gCgYIKoEcz1UBgi2hRANCAATQ0b9ruVKjcZz8OSulhybqCgf2Mcdg+4piZIOuEhQFB1LDX1De+luMr+xgzqWbfq/EZLE7OOvYIiE4iBC6wBlk";

//        BCECPublicKey publicKey = getPublicKey(Base64.decodeBase64(publicKeyStr));
//        BCECPrivateKey privateKey = getPrivateKey(Base64.decodeBase64(privateKeyStr));
        //        BCECPublicKey publicKey2 = getPublicKey(Base64.decodeBase64(publicKeyStr2));
//        BCECPrivateKey privateKey2 = getPrivateKey(Base64.decodeBase64(privateKeyStr2));


        // 调用方数据
//        JSONObject data = new JSONObject();
//        data.put("username", "keji");
//        data.put("name","柯基");
//        data.put("companyName", "中科极动");
//        data.put("email", "xxx@nbicc.com");
//        data.put("phone", "13800000000");
//        data.put("timestamp", System.currentTimeMillis()/1000);

//        String dataStr = "{\"userId\":565}";
        String dataStr = "{\"userId\":25012}";
//        String dataStr = "{\n" +
//                "    \"userId\": \"392\",\n" +
//                "    \"moduleType\": \"bss\"\n" +
//                "}";
//        String dataStr =  "{\n" +
//                "    \"ext\":{\n" +
//                "        \"invitationCode\":\"\"\n" +
//                "    },\n" +
//                "    \"flag\":\"rightcloud\",\n" +
//                "    \"company\":{\n" +
//                "        \"applicationScenario\":\"otherApplicationScenarios\",\n" +
//                "        \"orgName\":\"熊伟3\",\n" +
//                "        \"address\":\"熊伟5\",\n" +
//                "        \"industryType\":\"otherIndusty\",\n" +
//                "        \"contactName\":\"xw2\",\n" +
//                "        \"contactPhone\":\"13025062091\",\n" +
//                "        \"personnelSize\":\"personnelSize.1\"\n" +
//                "    },\n" +
//                "    \"user\":{\n" +
//                "        \"password\":\"584520Xw!\",\n" +
//                "        \"authUserId\":\"1\",\n" +
//                "        \"smscode\":\"123456\",\n" +
//                "        \"mobile\":\"13025062091\",\n" +
//                "        \"industry\":\"otherIndusty\",\n" +
//                "        \"account\":\"xiongwei1302\",\n" +
//                "        \"email\":\"xw4@qq.com\"\n" +
//                "    }\n" +
//                "}";
        JSONObject data = JSON.parseObject(dataStr);
        System.out.println("data: " + data);
        // 使用子方的私钥加签名
        String sign = sign(privateKey, data);
//        String sign = "MEUCIBg0fizA3t4MxXF9hmNlOLFeqLOJER27BhfHdL49y1vKAiEA80DOYWxN/+X/RD50f2AcmTM9EYq+l5VUTYE/lVNGtCo=";
        System.out.println("签名 " + sign);
        data.put("sign",sign);
        // 用接收方的公钥加密
        String encryptedString = encrypt(myPublicKey, data.toString());
        System.out.println("加密后字符串:" + encryptedString);
//        System.out.println("url:"+ URLEncoder.encode(encryptedString,"UTF-8"));
//        String encryptedString ="BJbhJsKQiqbgh7Ffsl8w3WDHFYQ8ApXmwNHxRJ12eK/Ef6+9h8ANgY2uDuZRWATMu2MX4bBFP5tRSVj7m8WZRgCY0aTXGU6fRQw2MtO2CiGGDfSXteliqLEGRbvpHuNERPTtja+VjeKhmdvW75XcRrrpySa6aqPgjUSJ8VHIJxpEVoX6KbEzN/tauUO++q2gQ0uaO4clePkWrrjWO8Cl+0IdUV6ADQcIgmkQRWyfYxWMIBPBQ/2kzF2cRbZII3JdQkgQkDQ1fScqvPR3DgIJjKBoGOjdi6QC5OBZ30kFQaViImRn7N5t3rZe/FMFaTeQ0uJE";

        String encode = URLEncoder.encode(encryptedString,"UTF-8");
        System.out.println("url加密"+encode);
        System.out.println(URLDecoder.decode(encode));

        //String encryptedString = URLUtil.decode("BPH6mCYrZP2I5E0YaTTRxA968QdXaEQPLNvX0YiDq678yHi4bQ89LP9eqQJuDfPLZa%2FTWNe7LAtQa0ly3%2BeZXDMebqOz%2Fe8DU3zxh3zhnQsKRHctx0ROGQRk5ITLxRsfNmV7StFtuU%2FUAo4TCAwszkRWTTS67WxE8kKupGzsTha0CNsikJxcNRs9YyKHn5GbX4Rmtx3E%2BTF3XiAU%2FK7haHzFaNPGCtYjjFCw1ToRhfQaIwGtpFbyPUennhzr1O2lnv6xONCzboaDWjd11iOt63sXcX6ZyNEVyQS9rhE%3D","UTF-8");
//        String encryptedString = UrlU.decode("BPH6mCYrZP2I5E0YaTTRxA968QdXaEQPLNvX0YiDq678yHi4bQ89LP9eqQJuDfPLZa%2FTWNe7LAtQa0ly3%2BeZXDMebqOz%2Fe8DU3zxh3zhnQsKRHctx0ROGQRk5ITLxRsfNmV7StFtuU%2FUAo4TCAwszkRWTTS67WxE8kKupGzsTha0CNsikJxcNRs9YyKHn5GbX4Rmtx3E%2BTF3XiAU%2FK7haHzFaNPGCtYjjFCw1ToRhfQaIwGtpFbyPUennhzr1O2lnv6xONCzboaDWjd11iOt63sXcX6ZyNEVyQS9rhE%3D","UTF-8");

        // 使用接收方的私钥解密
        String plaintext = decrypt(myPrivateKey, encryptedString);
        System.out.println("解密后字符串:" + plaintext);
        JSONObject result = JSON.parseObject(plaintext);
        // 使用发送方的公钥验签
        boolean verified = verify(publicKey, result);
        System.out.println("结果:" + result);
        System.out.println("验签结果: " + verified);
////        result.put("sign",sign1);
//        System.out.println("verified: " + verified);
//        boolean verified1 = verify(myPublicKey,result);
//        result.put("sign",sign2);
//        boolean verified2 = verify(myPublicKey,result);
//
//        System.out.println("verified1: " + verified1);
//        System.out.println("verified2: " + verified2);

//        String sign = sign(privateKey,data.toString());
//        System.out.println("sign: "+ sign);
//        System.out.println(verify(publicKey,data.toString(),sign));
    }
}

