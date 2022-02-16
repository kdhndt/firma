package be.vdab.firma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/geluksgetal")
public class GeluksgetalController {

    @GetMapping
    public String geluksgetal() {
        return "geluksgetal";
    }
}