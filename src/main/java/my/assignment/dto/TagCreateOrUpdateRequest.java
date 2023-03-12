package my.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.assignment.entity.enumeration.Color;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TagCreateOrUpdateRequest {
    private String name;
    private Color color;
}
