package my.assignment.entity;

import lombok.*;

import javax.persistence.*;
import my.assignment.entity.enumeration.Color;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TAG")
public class TagEntity {
    @Id
    private String name;

    @Enumerated(EnumType.STRING)
    private Color color;
}
