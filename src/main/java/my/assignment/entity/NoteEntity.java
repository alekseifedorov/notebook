package my.assignment.entity;

import java.util.Set;
import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table(name = "NOTE")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note")
    @SequenceGenerator(name = "note", sequenceName = "note_seq")
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private NotebookEntity notebook;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
        name = "tag_to_note",
        joinColumns = {@JoinColumn(name = "tag_name", nullable = false)},
        inverseJoinColumns = {@JoinColumn(name = "note_id", nullable = false)}
    )
    private Set<TagEntity> tags;
}
