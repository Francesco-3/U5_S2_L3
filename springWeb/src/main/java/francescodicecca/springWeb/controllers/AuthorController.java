package francescodicecca.springWeb.controllers;

import francescodicecca.springWeb.entities.Author;
import francescodicecca.springWeb.payloads.NewAuthorPayload;
import francescodicecca.springWeb.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAutor() { return this.authorService.findAllAuthors(); }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable UUID authorId) { return this.authorService.findAuthorById(authorId); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody NewAuthorPayload body) { return this.authorService.saveAuthor(body); }

    @PutMapping("/{authorId}")
    public Author getAuthorByIdAndUpdate(@PathVariable UUID authorId, @RequestBody NewAuthorPayload body)
    { return this.authorService.findAuthorByIdAndUpdate(authorId, body); }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAuthorByIdAndDelete(@PathVariable UUID authorId) { this.authorService.findAuthorByIdAndDelete(authorId); }
}
