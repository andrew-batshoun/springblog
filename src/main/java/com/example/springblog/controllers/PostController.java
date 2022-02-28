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
            postDao.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String viewEdit(@PathVariable long id, Model model){
        Post editPost = postDao.getById(id);
        model.addAttribute("post", editPost);

        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postEdit(@RequestParam(name = "title")String title, @RequestParam(name="body") String body, @PathVariable long id){
        Post editedPost = postDao.getById(id);
        editedPost.setTitle(title);
        editedPost.setBody(body);
        postDao.save(editedPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}
