package uz.abbos.resumebuilder.model;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = ("education_mapping"))
public class EducationMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = ("education_id"),insertable = false, updatable = false)
    private Education education;

    @ManyToOne
    @JoinColumn(name = ("resume_id"), insertable = false, updatable = false)
    private Resume resume;

    @Column(name = ("resume_id"))
    private Long resumeId;

    @Column(name = ("education_id"))
    private Long educationId;

    @Column(name = ("start_date"))
    private LocalDate startDate;

    @Column(name = ("end_date"))
    private LocalDate endDate;


}


