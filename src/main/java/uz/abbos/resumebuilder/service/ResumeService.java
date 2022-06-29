package uz.abbos.resumebuilder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.abbos.resumebuilder.dao.ResumeDao;
import uz.abbos.resumebuilder.dto.ResumeDto;
import uz.abbos.resumebuilder.exception.BadRequestException;
import uz.abbos.resumebuilder.model.Resume;


@Service
public class ResumeService {

    public ResumeService(ModelMapper modelMapper, ResumeDao resumeDao, UserService userService,
                         ImageService imageService, EducationMappingService educationMappingService,
                         ExperienceService experienceService) {

        this.modelMapper = modelMapper;
        this.resumeDao = resumeDao;
        this.userService = userService;
        this.imageService = imageService;
        this.educationMappingService = educationMappingService;
        this.experienceService = experienceService;
    }

    private final ModelMapper modelMapper;
    private final ResumeDao resumeDao;
    private final UserService userService;
    private final ImageService imageService;
    private final EducationMappingService educationMappingService;
    private final ExperienceService experienceService;


    @Transactional
    public ResumeDto get(Long id) {
        Resume resume = resumeDao.findResume(id);
        if (resume == null) {
            throw new BadRequestException("Resume not found");
        }
        ResumeDto resumeDto = modelMapper.map(resume, ResumeDto.class);
        resumeDto.setExperiences(experienceService.getByResumeId(id));
        resumeDto.setEducations(educationMappingService.getByResumeId(id));
        return resumeDto;
    }


    @Transactional
    public String create(ResumeDto resumeDto) {
//        try {
            userService.check(resumeDto.getUserId());

            imageService.check(resumeDto.getImage());

        Resume resume = modelMapper.map(resumeDto, Resume.class);

        resumeDao.saveResume(resume);

        resumeDto.getEducations().forEach(e -> e.setResumeId(resume.getId()));
        educationMappingService.create(resumeDto.getEducations());

            resumeDto.getExperiences().forEach(e -> e.setResumeId(resume.getId()));
            experienceService.create(resumeDto.getExperiences());

            return "Resume created";
//    }
//        catch (Exception e){
//                throw new BadRequestException("Resume create failed");
//            }
    }

    public String update(ResumeDto ResumeDto) {
        return null;
    }

    public String delete(Long id) {
        return null;
    }
}
