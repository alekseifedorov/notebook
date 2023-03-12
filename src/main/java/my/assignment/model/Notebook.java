package my.assignment.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Notebook {

    private Long id;

    private String title;

    private String description;

    private Set<ShortNote> notes;

    private Set<Tag> tags;

    private LocalDate createdOn;

    private LocalDate lastModified;
}
