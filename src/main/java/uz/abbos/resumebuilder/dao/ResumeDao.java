package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.Resume;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ResumeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public String saveResume(Resume resume) {
        entityManager.persist(resume);
        return "Resume created";
    }

    public Resume findResume(Long id) {
        return entityManager.find(Resume.class,id);
    }
}
