package org.walletuser.walletuser.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Service.AdminService;
import org.walletuser.walletuser.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersWithRole(@RequestParam String role) {
        return adminService.getUsersByRole(role);
    }

    @PutMapping("/users/{userId}/update")
    public ResponseEntity<UserDTO> updateAdmin(@PathVariable Long userId, @RequestBody User userDetails) {
        try {
            UserDTO updatedUser = adminService.updateAdmin(userId, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            logger.error("Error updating user: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/users/{userId}/active")
    public ResponseEntity<String> updateUserEnabled(@PathVariable Long userId, @RequestParam boolean enabled) {
        try {
            adminService.updateUserEnabledStatus(userId, enabled);
            return ResponseEntity.ok("User's enabled status updated successfully.");
        } catch (IllegalArgumentException e) {
            logger.warn("Attempt to update enabled status for non-existent user with ID: {}", userId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByRoleAndDateRange(
            @RequestParam String role,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return adminService.getUsersByRoleAndDateRange(role, startDate, endDate);
    }
}