package my.assignment.entity;

import java.time.LocalDate;
import java.util.Set;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "NOTEBOOK")
public class NotebookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notebook")
    @SequenceGenerator(name = "notebook", sequenceName = "notebook_sequence")
    private Long id;

    private String title;

    private String description;

    @OneToMany
    private Set<NoteEntity> notes;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
        name = "tag_to_notebook",
        joinColumns = {@JoinColumn(name = "tag_id", nullable = false)},
        inverseJoinColumns = {@JoinColumn(name = "notebook_id", nullable = false)}
    )
    private Set<TagEntity> tags;

    @CreatedDate
    private LocalDate createdOn;

    @LastModifiedDate
    private LocalDate lastModified;
}
