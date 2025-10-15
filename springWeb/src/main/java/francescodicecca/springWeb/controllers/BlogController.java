package francescodicecca.springWeb.controllers;

import francescodicecca.springWeb.entities.Blog;
import francescodicecca.springWeb.payloads.NewBlogPayload;
import francescodicecca.springWeb.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getBlog() { return this.blogService.findAll(); }

    @GetMapping("/{blogId}")
    public Blog getBlogById(@PathVariable UUID blogId) { return this.blogService.findById(blogId); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody NewBlogPayload body) { return this.blogService.saveBlog(body); }

    @PutMapping("/{blogId}")
    public Blog getBlogByIdAndUpdate(@PathVariable UUID blogId, @RequestBody NewBlogPayload body)
    { return this.blogService.findByIdAndUpdate(blogId, body); }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID blogId) { this.blogService.findByIdAndDelete(blogId); }
}
