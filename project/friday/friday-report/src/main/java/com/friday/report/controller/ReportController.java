package com.friday.report.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @GetMapping("reportOne")
    public String reportOne(Integer id){
        return "true";
    }
}
