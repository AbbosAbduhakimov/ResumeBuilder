package uz.abbos.resumebuilder.dao;

import org.springframework.stereotype.Repository;
import uz.abbos.resumebuilder.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CompanyDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Company findCompany(Long id) {
        return entityManager.find(Company.class,id);
    }

    public String saveCompany(Company company) {
        entityManager.persist(company);
        return "Company created";
    }

    public String updateCompany(Company company) {
        entityManager.merge(company);
        return "Company updated";
    }

    public String deleteCompany(Company company) {
        entityManager.remove(company);
        return "Company deleted";
    }
}
