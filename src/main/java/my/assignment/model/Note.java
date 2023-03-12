package my.assignment.model;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Note {

    private Long id;

    private String title;

    private String content;

    private ShortNotebook notebook;

    private List<Tag> tags;
}
