package com.avizhen.controller;

import com.avizhen.dto.UniverseCreateDto;
import com.avizhen.entity.Universe;
import com.avizhen.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UniverseController {
    private UniverseService universeService;

    @Autowired
    public UniverseController(UniverseService universeService) {
        this.universeService = universeService;
    }

    @GetMapping("/universes")
    public String showAllUniverses(Model model) {
        List<Universe> allUniversity = universeService.getAllUniversity();
        model.addAttribute("universes", allUniversity);

        return "universes";
    }

    @GetMapping("universes/new")
    public String newUniverse(Model model) {
        model.addAttribute("universe", new UniverseCreateDto());
        return "newUniverse";
    }

    @PostMapping("/universe")
    public String createUniverse(UniverseCreateDto universeCreateDto, Model model, RedirectAttributes redirectAttributes) {
        Universe universe = universeService.createUniverse(universeCreateDto);
        model.addAttribute("universe", universe);
        redirectAttributes.addFlashAttribute("msg", "Universe was created");
        return "redirect:/universes";
    }

    @DeleteMapping("/universe/{id}")
    public String delete(@PathVariable int id,RedirectAttributes redirectAttributes) {
        universeService.deleteUniverse(id);
        redirectAttributes.addFlashAttribute("msg", "Universe was deleted");
        return "redirect:/universes";
    }
}
