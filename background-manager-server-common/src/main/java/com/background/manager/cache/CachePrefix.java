package com.background.manager.cache;

/**
 * 缓存前缀，用于规范redis的使用场景
 *
 * @author ccsert
 * @date 2022/02/25
 */
public class CachePrefix {


    public static final  String HEADER = "LINKMALL:";
    /**
     * 货物前缀
     */
    public static String goodsPrefix = "GOODS:";


    /**
     * 商城首页
     */
    public static String mallIndexPrefix = HEADER +"MALLINDEX:";


    /**
     * 货物SKU前缀
     */
    public static String goodsSkuPrefix = "SKU:";


    /**
     * 用户认证前缀，一般就是存token
     */
    public static String authPrefix = "AUTH:";


    /**
     * 订单前缀
     */
    public static String orderPrefix = "ORDER:";

    /**
     * 统计前缀
     */
    public static String statisticsPrefix = "STATISTICS:";


    /**
     * 验证码次数前缀
     */
    public static String codePrefix = "CODE:";

    /**
     * 验证码有效期前缀
     */
    public static String codeValidityPrefix = "CODE:VALID:";

    /**
     * 商品分类
     */
    public static String categoryPrefix = "CATEGORY:";

    /**
     * 贸易商
     */
    public static String tradePrefix = "TRADE:";

    /**
     * 订单
     */
    public static String order = "ORDER:";

    /**
     * 订单号
     */
    public static String orderSn = "ORDERSN:";

    /**
     * 订单计划
     */
    public static String orderPlan = "ORDERPLAN:";

    /**
     * 工厂评价星级数据
     */
    public static String factoryEvaluateStar = "FACTORYEVALUATESTAR:";

    /**
     * 热门工厂前缀
     */
    public static  String popularFactory="POPULARFACTORY";

    /**
     * 延期申请号
     */
    public static String planPostSn = "PLANPOSTSN:";

    /**
     * 布隆过滤器Key
     */
    public static String bloom = "BLOOM:";

    /**
     * 对象存储临时token
     */
    public static String obsToken = "obsToken";

    /**
     * 商品计分
     */
    public static String goodsEvaluatePrefix = "GOODEVALUATE:";

    /**
     * 联系人
     */
    public static String contacts = "CONTACTS:";

    /**
     * 是否开启视频
     */
    public static String openVideo = "OPENVIDEO:";

	/**
	 * 付费服务信息
	 */
	public static String payServiceInfo = "PAYSERVICEINFO:";

	/**
	 * 工厂浏览记录
	 */
	public static String browsingFactory = "BROWSINGFACTORY:";

	/**
	 * 付款流水号
	 */
	public static String outTradeNo = "OUTTRADENO:";

    public static String obsKey = "obsKey";

    public static String getObsToken() {
        return HEADER + CachePrefix.obsToken;
    }

    public static String getBloom() {
        return HEADER + CachePrefix.bloom;
    }


    public static String getObsKey() {
        return HEADER + CachePrefix.obsKey;
    }

    /**
     * 获取工厂评价星级数据
     */
    public static String getFactoryEvaluateStar(String key) {
        return HEADER + CachePrefix.factoryEvaluateStar + key;
    }

    /**
     * 获取热门工厂数据
     */
    public static String getPopularFactory(String key){
        return HEADER+CachePrefix.popularFactory+key;
    }

    /**
     * 获取订单号
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getOrderSn(String key) {
        return HEADER + CachePrefix.orderSn + key;
    }

    /**
     * 获取订单
     * @param key 缓存key
     * @return
     */
    public static String getOrder(String key) {
        return HEADER + CachePrefix.order + key;
    }

    /**
     * 拼装订单计划key
     *
     * @param key 缓存key
     * @return
     */
    public static String getOrderPlan(String key) {
        return HEADER + CachePrefix.orderPlan + key;
    }

    /**
     * 获取延期申请号
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getPlanPostSn(String key) {
        return HEADER + CachePrefix.planPostSn + key;
    }


    /**
     * 贸易商拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getTradePrefix(String key) {
        return HEADER + CachePrefix.tradePrefix + key;
    }


    /**
     * 商品分类拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getCategoryPrefix(String key) {
        return HEADER + CachePrefix.categoryPrefix + key;
    }


    /**
     * 商品拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getGoodsPrefix(String key) {
        return HEADER + CachePrefix.goodsPrefix + key;
    }

    /**
     * 商品SKU拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getGoodsSkuPrefix(String key) {
        return HEADER + CachePrefix.goodsSkuPrefix + key;
    }


    /**
     * 订单拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getOrderPrefix(String key) {
        return HEADER + CachePrefix.orderPrefix + key;
    }


    /**
     * 统计拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getStatisticsPrefix(String key) {
        return HEADER + CachePrefix.statisticsPrefix + key;
    }


    /**
     * 验证码次数拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getCodePrefix(String key) {
        return HEADER + CachePrefix.codePrefix + key;
    }


    /**
     * 验证码有效期拼装key
     *
     * @param key 缓存key
     * @return {@link String}
     */
    public static String getCodeValidityPrefix(String key) {
        return HEADER + CachePrefix.codeValidityPrefix + key;
    }


    /**
     * 商品计分拼装key
     */
    public static String getGoodsEvaluatePrefix(String key) {
        return HEADER + CachePrefix.goodsEvaluatePrefix + key;
    }

    /**
     * 联系人拼装key
     */
    public static String getContactsPrefix(String key) {
        return HEADER + CachePrefix.contacts + key;
    }

    /**
     * 视频是否开启设置
     */
    public static String getOpenVideoPrefix() {
        return HEADER + CachePrefix.openVideo;
    }

	/**
	 * 付费服务信息
	 */
	public static String getPayServiceInfo(String key) {
		return HEADER + CachePrefix.payServiceInfo + key;
	}

	/**
	 * 工厂浏览记录
	 */
	public static String getBrowsingFactory(String key) {
		return HEADER + CachePrefix.browsingFactory + key;
	}

	/**
	 * 获取付款流水号
	 */
	public static String getOutTradeNo(String Key) {
		return HEADER + CachePrefix.outTradeNo + Key;
	}

    /**
     * 认证信息前缀
     *
     * @author ccsert
     * @date 2022/02/25
     */
    public enum AuthPrefix {
        /**
         * 访问令牌
         */
        ACCESS_TOKEN,
        /**
         * 刷新令牌
         */
        REFRESH_TOKEN;

        /**
         * 拼装key
         *
         * @param key 缓存key
         * @return {@link String}
         */
        public String getPrefix(String key) {
            return CachePrefix.authPrefix + "{" + this.name() + "}_" + key;
        }
    }
}
