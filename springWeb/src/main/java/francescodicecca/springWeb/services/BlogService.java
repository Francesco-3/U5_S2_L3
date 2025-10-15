package francescodicecca.springWeb.services;

import francescodicecca.springWeb.entities.Blog;
import francescodicecca.springWeb.exceptions.NotFoundException;
import francescodicecca.springWeb.payloads.NewBlogPayload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import java.util.*;

@Service
@Slf4j
public class BlogService {
    private List<Blog> blogDB = new ArrayList<>();

    //metodo che ritorna tutti i blog posts
    public List<Blog> findAll() { return this.blogDB; }

    //metodo che crea un nuovo blog post
    public Blog saveBlog(NewBlogPayload payload) {
        Blog newBlog = new Blog(payload.getCategorie(), payload.getTitle(), payload.getCover(), payload.getContent(), payload.getReading_time());
        this.blogDB.add(newBlog);
        log.info("Il blog " + newBlog.getTitle() + " è stato aggiunto al database!");
        return newBlog;
    }

    //metodo che ritorna un blog post specifico
    public Blog findById(UUID blogId) {
        Blog found = null;
        for (Blog blog : this.blogDB) {
            if (blog.getId() == blogId) found = blog;
        }

        if (found == null) throw new NotFoundException(blogId);
        return found;
    }

    //metodo che aggiorna un blog post specifico
    public Blog findByIdAndUpdate(UUID blogId, NewBlogPayload payload) {
        Blog found = null;
        for (Blog blog : this.blogDB) {
            if (blog.getId() == blogId) {
                found = blog;
                found.setCategorie(payload.getCategorie());
                found.setTitle(payload.getTitle());
                found.setCover(payload.getCover());
                found.setContent(payload.getContent());
                found.setReading_time(payload.getReading_time());
            }
        }

        if (found == null) throw new NotFoundException(blogId);
        return found;
    }

    //metodo che elimina un blog post specifico
    public void findByIdAndDelete(UUID blogId) {
        Blog found = null;
        for (Blog blog : this.blogDB) {
            if (blog.getId() == blogId) found = blog;
        }

        if (found == null) throw new NotFoundException(blogId);
        this.blogDB.remove(found);
    }
}
