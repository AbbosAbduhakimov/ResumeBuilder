package uz.abbos.resumebuilder.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ("education_name"))
    private String educationName;

    @Column(name = ("start_date"))
    private LocalDate startDate;

    @Column(name = ("end_date"))
    private LocalDate endDate;

    @OneToMany(mappedBy = ("education"),cascade = CascadeType.MERGE,orphanRemoval = true)
    private Set<EducationMapping> educationMappings;

}
