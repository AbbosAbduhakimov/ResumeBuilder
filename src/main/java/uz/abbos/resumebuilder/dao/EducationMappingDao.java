package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.EducationMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EducationMappingDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EducationMapping findEduMapping(Long id){
        return entityManager.find(EducationMapping.class,id);
    }

    public String saveEduMapping(Set<EducationMapping> educationMapping){
        for (EducationMapping mapping : educationMapping) {
            entityManager.persist(mapping);
        }
        return "EducationMapping created";
    }

    public String updateEduMapping(Set<EducationMapping> educationMapping){
        for (EducationMapping mapping : educationMapping) {
            entityManager.merge(mapping);
        }
        return "EducationMapping updated";
    }

    public String deleteEduMapping(EducationMapping educationMapping){
        entityManager.remove(educationMapping);
        return "EducationMapping deleted";
    }

    public Set<EducationMapping> findByResumeId(Long id) {
        String hql = "SELECT * FROM education_mapping WHERE resume_id = :id";
        Query query = entityManager
                .createNativeQuery(hql,EducationMapping.class)
                .setParameter("id",id);
        Set<EducationMapping> resultSet = (Set<EducationMapping>) query.getResultStream().collect(Collectors.toSet());
        return resultSet;
    }
}
