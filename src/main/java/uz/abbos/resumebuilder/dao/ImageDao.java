package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.Image;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ImageDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Image save(Image image) {
        entityManager.persist(image);
        return image;
    }

    public Image find(Long id) {
       return entityManager.find(Image.class,id);
    }
}
