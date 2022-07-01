package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    @Value("${server.port}")
    private String currentPort;

    @GetMapping
    public ResponseEntity<?> getMessage() {
        log.info("GET message endpoint invoked. --> Port: {}", currentPort);
        if(currentPort.equals("8088")) {
            throw new RuntimeException("8088 crash");
        }
        return ResponseEntity.ok("GET message endpoint invoked. ==> Port: " + currentPort);
    }

    @PostMapping
    public ResponseEntity<?> postMessage() {
        log.info("POST message endpoint invoked.");
        return ResponseEntity.ok("POST message endpoint invoked. ==> Port: " + currentPort);
    }

    @PutMapping
    public ResponseEntity<?> putMessage() {
        log.info("PUT message endpoint invoked.");
        return ResponseEntity.ok("PUT message endpoint invoked. ==> Port: " + currentPort);
    }

}
