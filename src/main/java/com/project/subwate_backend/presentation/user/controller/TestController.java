package com.project.subwate_backend.presentation.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("kakaoApiKey", "e7dbc4e23278a48b3f7718232661e39d");
        model.addAttribute("redirectUri", "http://localhost:8080/api/v1/auth/kakao/callback");
        return "login";
    }
}
