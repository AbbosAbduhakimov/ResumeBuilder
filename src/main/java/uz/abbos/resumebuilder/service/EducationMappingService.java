package uz.abbos.resumebuilder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.EducationMappingDao;
import uz.abbos.resumebuilder.dto.EducationMappingDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.EducationMapping;
import uz.abbos.resumebuilder.util.MapperUtil;

import java.util.Optional;
import java.util.Set;

@Service
public class EducationMappingService {

    public EducationMappingService(ModelMapper modelMapper, EducationMappingDao educationMappingDao, EducationService educationService) {
        this.modelMapper = modelMapper;
        this.educationMappingDao = educationMappingDao;
        this.educationService = educationService;
    }

    private final ModelMapper modelMapper;

    private final EducationMappingDao educationMappingDao;
    private final EducationService educationService;



    @Transactional
    public EducationMappingDto get(Long id) {
        EducationMapping educationMapping = educationMappingDao.findEduMapping(id);
        if (educationMapping == null){
            throw new BadRequestException("EducationMapping not found");
        }
        Set<EducationMapping> educationMappingSet = Set.of(educationMapping);
        EducationMappingDto dto = new EducationMappingDto();
        for (EducationMapping educationMap : educationMappingSet){
            dto.setId(educationMap.getId());
            dto.setEducationDto(educationService.get(educationMap.getEducation().getId()));
            dto.setEducationId(educationMap.getEducation().getId());
            dto.setStartDate(educationMap.getStartDate());
            dto.setEndDate(educationMap.getEndDate());
        }

        return dto;
    }

    @Transactional
    public String create(Set<EducationMappingDto> educationMappingDto) {
        try {

//            Set<EducationMapping> educationMappings = modelMapper.map(educationMappingDto,new TypeToken<Set<EducationMapping>>(){}.getType());

            Set<EducationMapping> educationMappings = Set.of(modelMapper.map(educationMappingDto, EducationMapping[].class));
            return educationMappingDao.saveEduMapping(educationMappings);
        }
        catch (Exception e){
            throw new BadRequestException("EducationMapping create failed");
        }
    }

    @Transactional
    public String update(Set<EducationMappingDto> educationMappingDto) {
        try {
            Set<EducationMapping> educationMappings = Set.of(modelMapper.map(educationMappingDto, EducationMapping[].class));
            return educationMappingDao.updateEduMapping(educationMappings);
        }
        catch (Exception e){
            throw new BadRequestException("EducationMapping update failed");
        }
    }

    @Transactional
    public String delete(Long id) {
        EducationMapping educationMapping = check(id);
        return educationMappingDao.deleteEduMapping(educationMapping);
    }

    private EducationMapping check(Long id) {
        Optional<EducationMapping> optional = Optional.ofNullable(educationMappingDao.findEduMapping(id));
        if (optional.isEmpty()){
            throw new BadRequestException("EducationMapping not found");
        }
        return optional.get();
    }



    public Set<EducationMappingDto> getByResumeId(Long id) {
        Set<EducationMapping> resultSet = educationMappingDao.findByResumeId(id);
        Set<EducationMappingDto> dtoSet = MapperUtil.mapAll(resultSet,EducationMappingDto.class);
        return dtoSet;
    }
}
