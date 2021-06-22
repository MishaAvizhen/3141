package com.avizhen.controller;

import com.avizhen.dto.LordCreateDto;
import com.avizhen.entity.Lord;
import com.avizhen.entity.Universe;
import com.avizhen.service.LordService;
import com.avizhen.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LordController {
    private LordService lordService;
    private UniverseService universeService;

    @Autowired
    public LordController(LordService lordService, UniverseService universeService) {
        this.lordService = lordService;
        this.universeService = universeService;
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
    @GetMapping("/lordTheEldest")
    public String showTheElderLords(Model model) {
        List<Lord> theEldestLords = lordService.findTheEldestLords();
        model.addAttribute("lords", theEldestLords);

        return "lords";
    }
    @GetMapping("/lord/{lordId}")
    public String showLordToChooseUniversity(Model model, @PathVariable int lordId) {
        List<Universe> allFreeUniversity = universeService.getAllFreeUniversity();
        model.addAttribute("universes", allFreeUniversity);
        model.addAttribute("targetLordId", lordId);

        return "universes";
    }
    @GetMapping("/universe/{lordId}/{universeId}")
    public String appointmentLordToUniversity(Model model, @PathVariable int lordId, @PathVariable int universeId) {
        lordService.appointToRulePlanet(lordId, universeId);
        return "redirect:/lords";
    }

}
