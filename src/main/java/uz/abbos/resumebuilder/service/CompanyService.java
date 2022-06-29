package uz.abbos.resumebuilder.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.CompanyDao;
import uz.abbos.resumebuilder.dto.CompanyDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.Company;

import java.util.Optional;


@Service
public class CompanyService {

    @Autowired
    public CompanyService(ModelMapper modelMapper, CompanyDao companyDao) {
        this.modelMapper = modelMapper;
        this.companyDao = companyDao;
    }

    private final ModelMapper modelMapper;

    private final CompanyDao companyDao;


    @Transactional
    public CompanyDto get(Long id) {
        Company company =  companyDao.findCompany(id);
        if (company == null){
            throw new BadRequestException("Company not found");
        }
        CompanyDto companyDto = modelMapper.map(company, CompanyDto.class);
        return companyDto;
    }


    @Transactional
    public String create(CompanyDto companyDto) {
        try {
            Company company = modelMapper.map(companyDto, Company.class);
            return companyDao.saveCompany(company);
        }
        catch (Exception e){
            throw new BadRequestException("Company create failed");
        }
    }

    @Transactional
    public String update(CompanyDto companyDto) {
        try{
            Company company = modelMapper.map(companyDto,Company.class);
            return companyDao.updateCompany(company);
        }
        catch (Exception e){
            throw new BadRequestException("Company update failed");
        }
    }


    @Transactional
    public String delete(Long id) {
        Company company = check(id);
        return companyDao.deleteCompany(company);
    }


    private Company check(Long id) {
        Optional<Company> optional = Optional.ofNullable(companyDao.findCompany(id));
        if (optional.isEmpty()){
            throw new BadRequestException("Company not found");
        }
        return optional.get();
    }
}
