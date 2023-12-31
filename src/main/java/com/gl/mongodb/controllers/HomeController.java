package com.gl.mongodb.controllers;


import com.gl.mongodb.model.News;
import com.gl.mongodb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private NewsService newsService;
    @GetMapping("/")
    public String root(Model model){
        List<News> news = newsService.fetchAll();
        model.addAttribute("news",news);
        return "index";
        }

    }
