package my.assignment.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShortNotebook {

    private Long id;

    private String title;

    private String description;

    private LocalDate createdOn;

    private LocalDate lastModified;
}
