package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController("/loginV1")
public class LoginController {

    // /login?name=key
    // /myName => "sim

    // HashMap<String, String> sessionMap = new HashMap<>();

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        // sessionMap.put(session.getId(), name);
        session.setAttribute("name", name);


        return "saved";
    }

    @GetMapping("/myName")
    public String myName(HttpSession session) {
        // String myName = sessionMap.get(session.getId());
        String myName = (String) session.getAttribute("name");

        return myName;
    }
}
