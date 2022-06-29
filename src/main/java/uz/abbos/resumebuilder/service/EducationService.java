package uz.abbos.resumebuilder.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.EducationDao;
import uz.abbos.resumebuilder.dto.EducationDto;
import uz.abbos.resumebuilder.dto.EducationMappingDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.Education;

import java.util.Optional;
import java.util.Set;

@Service
public class EducationService {

    @Autowired
    public EducationService(ModelMapper modelMapper, EducationDao educationDao) {
        this.modelMapper = modelMapper;
        this.educationDao = educationDao;
    }

    private final ModelMapper modelMapper;

    private final EducationDao educationDao;


    @Transactional
    public EducationDto get(Long id) {
        Education education =  educationDao.findEducation(id);
        if (education == null){
            throw new BadRequestException("Education not found");
        }
        EducationDto educationDto = modelMapper.map(education, EducationDto.class);
        return educationDto;
    }


    @Transactional
    public String create(EducationDto educationDto) {
        try {
            Education education = modelMapper.map(educationDto, Education.class);
            return educationDao.saveEducation(education);
        }
        catch (Exception e){
            throw new BadRequestException("Education create failed");
        }
    }

    @Transactional
    public String update(EducationDto educationDto) {
        try{
            Education education = modelMapper.map(educationDto,Education.class);
            return educationDao.updateEducation(education);
        }
        catch (Exception e){
            throw new BadRequestException("Education update failed");
        }
    }


    @Transactional
    public String delete(Long id) {
        Education education = check(id);
        return educationDao.deleteEducation(education);
    }


    private Education check(Long id) {
        Optional<Education> optional = Optional.ofNullable(educationDao.findEducation(id));
        if (optional.isEmpty()){
            throw new BadRequestException("Education not found");
        }
        return optional.get();
    }


}
