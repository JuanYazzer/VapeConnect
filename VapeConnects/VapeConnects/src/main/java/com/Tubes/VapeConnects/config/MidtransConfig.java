package com.Tubes.VapeConnects.config;

import com.midtrans.Midtrans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class MidtransConfig {

    @Value("${midtrans.server.key}")
    private String serverKey;

    @Value("${midtrans.client.key}")
    private String clientKey;

    @PostConstruct
    public void init() {
        Midtrans.serverKey = serverKey;
        Midtrans.clientKey = clientKey;
        Midtrans.isProduction = false; // sandbox
    }
}
