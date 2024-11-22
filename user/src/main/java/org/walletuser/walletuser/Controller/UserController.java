package org.walletuser.walletuser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.Service.UserService;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService updateService;

    @PostMapping("/updateuser")
    public ResponseEntity updateUser(@RequestBody User user) {

        return updateService.updateUser(user);
    }
}
