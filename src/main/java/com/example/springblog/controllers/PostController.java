package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")

    public String post(Model model){
        List<Post> allPosts = new ArrayList<>();
        Post postOne = new Post(1, "Java and frameworks", "Java is a language that is statically typed");
        Post postTwo = new Post(2, "HTML 5", "HTML is considered a markup language");
        allPosts.add(postOne);
        allPosts.add(postTwo);

        model.addAttribute("allPosts", allPosts);



        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postId(@PathVariable long id, Model model){
        Post postOne = new Post(1, "Java and frameworks", "Java is a language that is statically typed");

            model.addAttribute("title", postOne.getTitle());
            model.addAttribute("body", postOne.getBody());

            String message = "Sorry that post isn't available";
            model.addAttribute("message", message);




        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreate(){
        return " You can view the form for create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
        return "You can create a new post here";
    }
}
