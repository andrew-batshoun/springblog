package com.example.springblog.controllers;

import com.example.springblog.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoriesController {
    private CategoryRepository categoryDao;

    public CategoriesController(CategoryRepository categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/category/{id}")
    public String showCategory(@PathVariable long id, Model model){
        model.addAttribute("posts", categoryDao.getById(id).getPosts());
        model.addAttribute("category", categoryDao.getById(id).getCategory());

        return "genre";
    }
}
