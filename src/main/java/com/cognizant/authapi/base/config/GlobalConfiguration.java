package com.cognizant.authapi.base.config;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.security.GeneralSecurityException;

/**
 * Created by 784420 on 7/12/2019 7:34 PM
 */
@Configuration
@Slf4j
@EnableAsync
public class GlobalConfiguration {
    private static InetSocketAddress address;

    @Value("${app.uri.get.proxy}")
    private String proxyURI;

    @Autowired
    HttpProxySelectorBuilder httpProxySelectorBuilder;
    @PostConstruct
    private void init() {
        address = getProxy(URI.create(proxyURI));
    }

    @Bean
    public HttpTransport getHttpTransport() throws GeneralSecurityException {
        HttpTransport httpTransport = null;
        if (address != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            httpTransport = new NetHttpTransport.Builder()
                    .doNotValidateCertificate()
                    .setProxy(proxy).build();
        } else {
            httpTransport = new NetHttpTransport();
        }
        return httpTransport;
    }

    private InetSocketAddress getProxy(URI uri) {
        System.setProperty("java.net.useSystemProxies", "true");
        Proxy proxy = ProxySelector.getDefault().select(uri).iterator().next();
        log.info("proxy type : " + proxy.type());
        InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();

        if (inetSocketAddress == null) {
            log.info("No Proxy");
        } else {
            log.info("proxy hostname : " + inetSocketAddress.getHostName());
            log.info("proxy port : " + inetSocketAddress.getPort());
        }
        return inetSocketAddress;
    }

}
