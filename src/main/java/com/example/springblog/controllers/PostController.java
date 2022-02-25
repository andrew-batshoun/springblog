package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String post(Model model){
        List<Post> allPosts = postDao.findAll();
//        Post postOne = new Post(1, "Java and frameworks", "Java is a language that is statically typed");
//        Post postTwo = new Post(2, "HTML 5", "HTML is considered a markup language");
//        allPosts.add(postOne);
//        allPosts.add(postTwo);
//
//        model.addAttribute("allPosts", allPosts);
        model.addAttribute("allPosts", allPosts);


        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postId(@PathVariable long id, Model model){
//        Post postOne = new Post(1, "Java and frameworks", "Java is a language that is statically typed");
//
//            model.addAttribute("title", postOne.getTitle());
//            model.addAttribute("body", postOne.getBody());
//
//            String message = "Sorry that post isn't available";
//            model.addAttribute("message", message);
        Post onePost = postDao.findPostById(id);
        model.addAttribute("onePost", onePost);


        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreate(Model model){

        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@ModelAttribute Post post){
        System.out.println("hello"+ post);

            postDao.save(post);
        return "redirect:/posts";
    }
}
