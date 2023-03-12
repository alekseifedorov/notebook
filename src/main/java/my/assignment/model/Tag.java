package my.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.assignment.entity.enumeration.Color;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tag {

    private String name;

    private Color color;
}
