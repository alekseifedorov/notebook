package my.assignment.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotebookCreateOrUpdateRequest {
    private String title;
    private String description;
    @Builder.Default
    private Set<String> tags = new HashSet<>();
}
