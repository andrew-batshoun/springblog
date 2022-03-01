package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String post(Model model) {
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);


        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postId(@PathVariable long id, Model model) {

        Post onePost = postDao.findPostById(id);
        model.addAttribute("onePost", onePost);


        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreate(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreate(@ModelAttribute Post post) {
        User user = userDao.getById(1L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEdit(@PathVariable long id, Model model) {
        Post editPost = postDao.getById(id);
        model.addAttribute("post", editPost);

        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @ModelAttribute Post post) {
        Post postEdit = postDao.getById(id);
        postEdit.setTitle(post.getTitle());
        postEdit.setBody(post.getBody());
        postDao.save(postEdit);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}
