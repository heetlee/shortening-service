package com.example.demo.shorten.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShorteningController {

  @GetMapping(value = "/")
  public String searchUrl(Model model) {
    return "searchUrl";
  }
}
