package francescodicecca.springWeb.repoitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import francescodicecca.springWeb.entities.Author;
import java.util.*;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);
}
