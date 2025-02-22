package edu.miu.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class CloudConfigClient {
    @Value("${welcome.message}")
    String welcomeText;

    @GetMapping(value = "/")
    public String welcomeText() {
        return welcomeText;
    }
}
