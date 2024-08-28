package com.floristeria.floristeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactoController {

    @GetMapping("/contactanos")
    public String contactanos() {
        return "contactanos";
    }
}