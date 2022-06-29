package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.Experience;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ExperienceDao {


    @PersistenceContext
    private EntityManager entityManager;


    public Experience findExperience(Long id) {
        return entityManager.find(Experience.class,id);
    }

    public String saveExperience(Set<Experience> experiences) {
        for (Experience experience : experiences) {
             entityManager.persist(experience);
        }
        return "Experience created";
    }



    public String updateExperience(Set<Experience> experiences) {
        for (Experience experience : experiences) {
            entityManager.merge(experience);
        }
        return "Experience updated";
    }

    public String deleteExperience(Experience experience) {
        entityManager.remove(experience);
        return "Experience deleted";
    }

    public Set<Experience> findByResumeId(Long id) {
        String hql = "SELECT * FROM experiences WHERE resume_id = :id";
        Query query = entityManager
                .createNativeQuery(hql, Experience.class)
                .setParameter("id",id);
        Set<Experience> resultSet = (Set<Experience>) query.getResultStream().collect(Collectors.toSet());
        return resultSet;

    }
}
