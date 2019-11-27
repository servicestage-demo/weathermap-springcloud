package com.huawei.weather.sample.config;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "sslEnabled", havingValue = "true")
public class WebConfiguration {
    @Bean
    public SSLContext sslContext() throws Exception {
        SSLContext context = SSLContext.getInstance("SSL");
        TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                    throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                    throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        context.init(null, new TrustManager[]{trustManager}, null);
        return context;
    }

    @Bean
    public DiscoveryClientOptionalArgs discoveryClientOptionalArgs(SSLContext sslContext) {
        DiscoveryClientOptionalArgs args = new DiscoveryClientOptionalArgs();
        EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
        builder.withClientName("HW-Eureka-PROVIDER")
                .withMaxTotalConnections(10)
                .withMaxConnectionsPerHost(10)
                .withCustomSSL(sslContext)
                .withHostnameVerifier((s, sslSession) -> true);
        args.setEurekaJerseyClient(builder.build());
        return args;
    }
}
