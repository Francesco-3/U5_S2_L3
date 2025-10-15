package francescodicecca.springWeb.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewAuthorPayload {
    private String name;
    private String surname;
    private String email;
    private String bondDate;
    private String avatar;
}
