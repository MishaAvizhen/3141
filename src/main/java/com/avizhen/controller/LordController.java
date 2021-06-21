package com.avizhen.controller;

import com.avizhen.entity.Lord;
import com.avizhen.service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LordController {
    private LordService lordService;

    @Autowired
    public LordController(LordService lordService) {
        this.lordService = lordService;
    }

    @GetMapping("/lords")
    public String showAllLords(Model model) {
        List<Lord> theEldestLords = lordService.findTheEldestLords();
        model.addAttribute("theEldestLords", theEldestLords);

        return "index";
    }

}
