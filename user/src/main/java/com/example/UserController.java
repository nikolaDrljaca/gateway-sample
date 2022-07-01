package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Value("${server.port}")
    private String currentPort;

    @GetMapping
    public ResponseEntity<?> getUser() {
        log.info("GET user endpoint invoked.");
        return ResponseEntity.ok("Get user endpoint invoked. " + currentPort);
    }

    @PostMapping
    public ResponseEntity<?> postUser() {
        log.info("POST user endpoint invoked.");
        return ResponseEntity.ok("Post user endpoint invoked. " + currentPort);
    }

    @PutMapping
    public ResponseEntity<?> putUser() {
        log.info("PUT user endpoint invoked.");
            return ResponseEntity.ok("Put user endpoint invoked. " + currentPort);
    }
}
