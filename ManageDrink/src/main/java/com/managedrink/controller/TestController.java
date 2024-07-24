package com.managedrink.controller;

import com.managedrink.entity.PermissionEnum;
import com.managedrink.entity.RequirePermission;
import com.managedrink.services.implement.PermissionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private PermissionServiceImpl permissionService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/data")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> getData(HttpServletRequest request) {
        // Kiểm tra quyền đọc dữ liệu
        if (permissionService.hasPermission(request, PermissionEnum.READ)) {
            // Logic để lấy dữ liệu
            return ResponseEntity.ok("Data retrieved");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission to read data");
        }
    }
}