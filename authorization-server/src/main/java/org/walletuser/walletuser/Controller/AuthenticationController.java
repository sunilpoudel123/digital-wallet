package org.walletuser.walletuser.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController implements ErrorController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
