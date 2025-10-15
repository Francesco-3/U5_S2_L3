package francescodicecca.springWeb.payloads;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewBlogPayload {
    private String categorie;
    private String title;
    private String cover;
    private String content;
    private int reading_time;
    public UUID author;
}
