package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import com.example.springblog.repositories.PostRepository;
import com.example.springblog.repositories.UserRepository;
import com.example.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String post(Model model) {
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);


        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postId(@PathVariable long id, Model model) {

        Post onePost = postDao.getById(id);
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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);
//        String subject = "New Post!";
//        String body = "A new post was created by " + user.getUsername();
//        emailService.prepareAndSend(post, subject, body);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEdit(@PathVariable long id, Model model) {
        Post editPost = postDao.getById(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (editPost.getUser().getId() == loggedInUser.getId()) {

            model.addAttribute("post", editPost);
            return "/posts/edit";
        } else {
            return "redirect:/posts";
        }


    }

    @PostMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @ModelAttribute Post post) {
//        Post postEdit = postDao.getById(id);
//        postEdit.setTitle(post.getTitle());
//        postEdit.setBody(post.getBody());
        User user = postDao.getById(id).getUser();
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String postDelete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

}
