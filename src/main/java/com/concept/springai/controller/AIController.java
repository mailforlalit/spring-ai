package com.concept.springai.controller;

import com.concept.springai.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AIController {

    @Autowired
    ChatService chatService;

    @PostMapping("/ask-ai")
    public ResponseEntity<String> askAi(@RequestBody String prompt) {
        return ResponseEntity.ok(chatService.askAI(prompt));
    }
}
