package com.cognizant.authapi.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class HttpProxySelectorBuilder {

    @Value("${http.proxyExclude:127.0.0.1}")
    String proxyExclude;

    public ProxySelector buildFor(Proxy proxy) {
        Predicate excluded = Pattern.compile(
                Stream.of(Objects.toString(proxyExclude, "").split(";")).collect(Collectors.joining("|"))).asPredicate();
        return new ProxySelector() {

            private boolean excluded(URI uri) {
                return excluded.test((uri.getHost()));
            }

            @Override
            public List<Proxy> select(URI uri) {
                if (!excluded(uri)) {
                    log.debug("selected proxy '{}' for '{}'", proxy.address(), uri.getHost());
                    return Collections.singletonList(proxy);
                }
                log.debug("ignored proxy for '{}'", uri.getHost());
                return Collections.emptyList();
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                log.error("error '{}' while connecting to {} with proxy {}", ioe.getMessage(), uri.getHost(), sa);
            }
        };
    }


}
