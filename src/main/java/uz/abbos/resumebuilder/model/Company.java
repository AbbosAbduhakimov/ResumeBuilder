package uz.abbos.resumebuilder.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = ("companies"))
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ("company_name"))
    private String companyName;

    @Column(name = ("company_address"))
    private String companyAddress;

    private String email;

    @Column(name = ("job_position"))
    private String jobPosition;

    @Column(name = ("job_title"))
    private String jobTitle;

}
