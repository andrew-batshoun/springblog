package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String post(){
        return "This is the index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable long id){
        return "you are seeing post id which is " + id;
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
