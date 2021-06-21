package com.avizhen.controller;

import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
        List<Lord> allLords = lordService.findAllLords();
        model.addAttribute("lords", allLords);

        return "lords";
    }
    @GetMapping("lords/new")
    public String newLord(Model model) {
        model.addAttribute("lord", new LordCreateDto());
        return "newLord";
    }
    @PostMapping("/lord")
    public String createLord(LordCreateDto lordCreateDto, Model model) {
        Lord lord = lordService.createLord(lordCreateDto);
        model.addAttribute("lord", lord);
        return "redirect:/lords";
    }
    @GetMapping("/lordWithoutUniverse")
    public String showAllLordsWithoutUniversity(Model model) {
        List<Lord> lordWithoutUniverse = lordService.findLordWithoutPlanet();
        model.addAttribute("lords", lordWithoutUniverse);

        return "lords";
    }

}
