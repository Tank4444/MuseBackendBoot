package ru.chuikov.MuseBackendBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {

    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){return "Test OK";}

    @RequestMapping("/testFull")
    public String testPage(){return "test";}
}
