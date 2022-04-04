package com.example.springblog.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "This needs a title")
    @Column(nullable = false, length = 100)
    private String title;

    @NotBlank(message = "This needs a body")
    @Column(nullable = false, length = 1000)
    private String body;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_categories",
            joinColumns = {@JoinColumn(name="post_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Categories> categories;



    public Post() {
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(long id, String title, String body, User user, List<Categories> categories) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
