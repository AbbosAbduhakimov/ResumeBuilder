package uz.abbos.resumebuilder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.ExperienceDao;
import uz.abbos.resumebuilder.dto.ExperienceDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.Experience;
import uz.abbos.resumebuilder.util.MapperUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ExperienceService {

    public ExperienceService(ModelMapper modelMapper, ExperienceDao experienceDao) {
        this.modelMapper = modelMapper;
        this.experienceDao = experienceDao;

    }

    private final ModelMapper modelMapper;

    private final ExperienceDao experienceDao;


    @Transactional
    public Set<ExperienceDto> get(Long id) {
        Experience experience = experienceDao.findExperience(id);
        if (experience == null){
            throw new BadRequestException("Experience not found");
        }
        Set<ExperienceDto> experienceDto = Set.of(modelMapper.map(experience,ExperienceDto[].class));
        return experienceDto;
    }

    @Transactional
    public String create(Set<ExperienceDto> experienceDto) {
        try {
            Set<ExperienceDto> dto = converter(experienceDto);
            Set<Experience> experiences = Set.of(modelMapper.map(dto, Experience[].class));
            return experienceDao.saveExperience(experiences);
        }
        catch (Exception e){
            throw new BadRequestException("Experience create failed");
        }
    }

    @Transactional
    public String update(Set<ExperienceDto> experienceDto) {
        try {
            Set<Experience> experiences = Set.of(modelMapper.map(experienceDto, Experience[].class));
            return experienceDao.updateExperience(experiences);
        }
        catch (Exception e){
            throw new BadRequestException("Experience update failed");
        }
    }

    @Transactional
    public String delete(Long id) {
        Experience experience = check(id);
        return experienceDao.deleteExperience(experience);
    }

    private Experience check(Long id) {
        Optional<Experience> optional = Optional.ofNullable(experienceDao.findExperience(id));
        if (optional.isEmpty()){
            throw new BadRequestException("Experience not found");
        }
        return optional.get();
    }

    public Set<ExperienceDto> converter(Set<ExperienceDto> experienceDto){
        long expYear;
        long expMonth;
        for (ExperienceDto dto : experienceDto) {
            expYear = ChronoUnit.YEARS.between(dto.getStartDate(),dto.getEndDate());
            dto.setExperienceYear(expYear);
            expMonth = ChronoUnit.MONTHS.between(dto.getStartDate(),dto.getEndDate());
            dto.setExperienceMonth(expMonth);
            experienceDto.add(dto);
        }
        return experienceDto;
    }

    @Transactional
    public Set<ExperienceDto> getByResumeId(Long id) {
        Set<Experience> resultSet = experienceDao.findByResumeId(id);
        Set<ExperienceDto> dtoSet = MapperUtil.mapAll(resultSet,ExperienceDto.class);
        return dtoSet;
    }

}
