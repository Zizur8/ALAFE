
package com.vs.alafe.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainAppController {
    @GetMapping
    public String mainApp(Model model) {
        return "mainApp/main"; 
    }
}