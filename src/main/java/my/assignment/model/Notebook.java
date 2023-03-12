package my.assignment.model;

import java.time.LocalDate;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Notebook {

    private Long id;

    private String title;

    private String description;

    private List<ShortNote> notes;

    private List<Tag> tags;

    private LocalDate createdOn;

    private LocalDate lastModified;
}
