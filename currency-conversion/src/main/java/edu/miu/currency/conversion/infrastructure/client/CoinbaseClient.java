package edu.miu.currency.conversion.infrastructure.client;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Component
public class CoinbaseClient {

    @Value("${coinbase.api.key}")
    private String apiKey;

    @Value("${coinbase.api.secret}")
    private String apiSecret;

    @Value("${coinbase.api.passphrase}")
    private String apiPassphrase;

    @Value("${coinbase.api.profileId}")
    private String profileId;

    @Value("${coinbase.api.url}")
    private String COINBASE_API_URL;

    private RestClient restClient;

    @PostConstruct
    public void init() {
        this.restClient = RestClient.create();
    }

    public String convert(String from, String to, String amount) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String method = "POST";
            String requestPath = "/conversions";

            String nonce = UUID.randomUUID().toString();
            String body = String.format(
                    "{\"profile_id\":\"%s\",\"from\":\"%s\",\"to\":\"%s\",\"amount\":\"%s\",\"nonce\":\"%s\"}",
                    profileId, from, to, amount, nonce
            );

            String prehash = timestamp + method + requestPath + body;
            String signature = sign(prehash, apiSecret);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("cb-access-key", apiKey);
            headers.set("cb-access-passphrase", apiPassphrase);
            headers.set("cb-access-sign", signature);
            headers.set("cb-access-timestamp", timestamp);

            ResponseEntity<String> response = restClient.post()
                    .uri(COINBASE_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .body(body)
                    .retrieve()
                    .toEntity(String.class);

            return response.getBody();

        } catch (RestClientException e) {
            throw new RuntimeException("Coinbase API call failed", e);
        } catch (Exception e) {
            throw new RuntimeException("Error signing Coinbase request", e);
        }
    }

    private String sign(String message, String secret) throws Exception {
        Mac sha256Mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
        sha256Mac.init(secretKey);
        return Base64.getEncoder().encodeToString(sha256Mac.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }
}