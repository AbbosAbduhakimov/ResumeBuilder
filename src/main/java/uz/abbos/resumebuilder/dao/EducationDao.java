package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.Education;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EducationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Education findEducation(Long id) {
        return entityManager.find(Education.class,id);
    }

    public String saveEducation(Education education) {
        entityManager.persist(education);
        return "Education created";
    }

    public String updateEducation(Education education) {
        entityManager.merge(education);
        return "Education updated";
    }

    public String deleteEducation(Education education) {
        entityManager.remove(education);
        return "Education deleted";
    }
}
