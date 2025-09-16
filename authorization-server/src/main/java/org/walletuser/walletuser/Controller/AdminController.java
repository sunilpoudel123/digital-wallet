package org.walletuser.walletuser.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.Service.AdminService;
import org.walletuser.walletuser.Service.UserService;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{role}/users")
    public ResponseEntity<List<User>> getUsersWithRole(@PathVariable String role) {

        List<User> users = adminService.getUsersByRole(role);

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 if no users are found
        }

        return ResponseEntity.ok(users); // HTTP 200 with the list of users
    }

    @PostMapping("/updateadmin")
    public ResponseEntity updateAdmin(@RequestBody User user) {

        return adminService.updateAdmin(user);
    }

    @PutMapping("/activeuser/{userId}")
    public ResponseEntity<?> updateUserEnabled(@PathVariable Long userId) {
        try {
            adminService.updateUserEnabled(userId);
            return ResponseEntity.ok("User's enabled status updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{role}/usersInRange")
    public ResponseEntity<List<User>> getUsersByRoleAndDateRange(
            @PathVariable String role,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        List<User> users = adminService.getUsersByRoleAndDateRange(role, start, end);

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 if no users are found
        }

        return ResponseEntity.ok(users); // HTTP 200 with the list of users
    }
}
