package com.background.manager.util;

import org.apache.http.ssl.TrustStrategy;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class AnyTrustStrategy implements TrustStrategy {

    @Override
    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        return true;
    }
}
