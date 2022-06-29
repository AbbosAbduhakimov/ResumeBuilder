package uz.abbos.resumebuilder.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = ("experiences"))
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ("resume_id"),updatable = false,insertable = false)
    private Resume resume;

    @Column(name = ("resume_id"))
    private Long resumeId;

    @Column(name = ("experience_year"))
    private Long experienceYear;

    @Column(name = ("experience_month"))
    private Long experienceMonth;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @Column(name = ("start_date"))
    private LocalDate startDate;

    @Column(name = ("end_date"))
    private LocalDate endDate;



}
