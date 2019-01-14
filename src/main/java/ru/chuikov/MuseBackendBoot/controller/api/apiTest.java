package ru.chuikov.MuseBackendBoot.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/test")
public class apiTest {

    @GetMapping
    public String testGet()
    {
        return "test";
    }

    @PostMapping
    @ResponseBody
    public String testPost() { return "Test OK";}
}
