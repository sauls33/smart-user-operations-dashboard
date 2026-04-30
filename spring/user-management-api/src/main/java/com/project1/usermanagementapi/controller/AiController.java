package com.project1.usermanagementapi.controller;

import com.project1.usermanagementapi.dto.UserSummaryResponse;
import com.project1.usermanagementapi.service.AiUserSummaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiUserSummaryService aiService;

    public AiController(AiUserSummaryService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/user-summary")
    public UserSummaryResponse generateSummary() {
        return buildSummaryResponse();
    }

    @GetMapping("/user-summary")
    public UserSummaryResponse getSummary() {
        return buildSummaryResponse();
    }

    private UserSummaryResponse buildSummaryResponse() {
        String summary = aiService.generateSummary();
        return new UserSummaryResponse(summary);
    }
}