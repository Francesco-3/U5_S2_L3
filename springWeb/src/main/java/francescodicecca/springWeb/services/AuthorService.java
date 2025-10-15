package francescodicecca.springWeb.services;

import francescodicecca.springWeb.entities.Author;
import francescodicecca.springWeb.exceptions.BadRequestException;
import francescodicecca.springWeb.exceptions.NotFoundException;
import francescodicecca.springWeb.payloads.NewAuthorPayload;
import francescodicecca.springWeb.repoitories.AuthorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.*;

@Service
@Slf4j
public class AuthorService {
    @Autowired
    private AuthorsRepository authorsRepository;

    public Page<Author> findAllAuthors(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.authorsRepository.findAll(pageable);
    }

    public Author saveAuthor(NewAuthorPayload payload) {
        this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
            throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");
        });

        Author author = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getBondDate());
        author.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());
        log.info("L'autore " + author.getName() + " è stato salvato correttamente!");
        return author;
    }

    public Author findAuthorById(UUID authorId) {
        return this.authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findAuthorByIdAndUpdate(UUID authorId, NewAuthorPayload payload) {
        Author found = this.findAuthorById(authorId);

        if (!found.getEmail().equals(payload.getEmail())) {
            this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
              }
            );
        }

        // 3. Modifico l'utente trovato nel db
        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setPassword(payload.getPassword());
        found.setAvatarURL("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());

        // 4. Salvo
        User modifiedUser = this.usersRepository.save(found);

        // 5. Log
        log.info("L'utente con id " + modifiedUser.getId() + " è stato modificato correttamente");

        // 6. Return dell'utente modificato
        return modifiedUser;
    }

    public void findAuthorByIdAndDelete(long authorId) {
        Author found = null;
        for (Author author : this.authorDB) {
            if (author.getId() == authorId) found = author;
        }

        if (found == null) throw new NotFoundException(authorId);
        this.authorDB.remove(found);
    }
}
