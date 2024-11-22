package org.walletuser.walletuser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Service.RegisterService;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/createuser")
    public ResponseEntity registerUser(@RequestBody User user) {
        return registerService.registerUser(user);
    }

    @PostMapping("/createadmin")
    public ResponseEntity registerAdmin(@RequestBody User user) {

        return registerService.registerAdmin(user);

    }
}
