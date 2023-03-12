package my.assignment.dto;

import java.util.ArrayList;
import java.util.List;
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
    private List<String> tags = new ArrayList<>();
}
