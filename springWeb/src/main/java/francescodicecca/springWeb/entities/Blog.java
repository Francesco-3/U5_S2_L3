package francescodicecca.springWeb.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "blogPosts")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String categorie;
    private String title;
    private String cover;
    private String content;
    private int reading_time;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Blog(String categorie, String title, String cover, String content, int reading_time) {
        this.categorie = categorie;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.reading_time = reading_time;
    }
}
