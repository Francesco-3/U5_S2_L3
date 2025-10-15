package francescodicecca.springWeb.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String name;
    private String surname;
    private String email;
    private String bondDate;
    private String avatar;

    public Author(String name, String surname, String email, String bondDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.bondDate = bondDate;
        this.avatar = generateAvatarUrl(name, surname);
    }

    private String generateAvatarUrl(String name, String surname) {
        String fullName = name + "+" + surname;
        return "https://ui-avatar.com/api/?name=" + fullName;
    }
}
