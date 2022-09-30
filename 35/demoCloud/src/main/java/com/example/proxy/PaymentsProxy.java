package com.example.proxy;

import com.example.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
public class PaymentsProxy {

    private final RestTemplate rest;

    @Value("${name.service.url}")
    private String paymentsServiceUrl;

    public PaymentsProxy(RestTemplate rest) {
        this.rest = rest;
    }

    public Payment createPayment(Payment payment) {
        String uri = paymentsServiceUrl + "/payment";

        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", UUID.randomUUID().toString());

        HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, headers);

        ResponseEntity<Payment> response =
                rest.exchange(uri,
                        HttpMethod.POST,
                        httpEntity,
                        Payment.class);

        return response.getBody();
    }
}


//
//@FeignClient(name = "payments", url = "${name.service.url}")
//    public interface PaymentsProxy {
//
//        @PostMapping("/payment")
//        Payment createPayment(
//                @RequestHeader String requestId,
//                @RequestBody Payment payment);
//
//    }