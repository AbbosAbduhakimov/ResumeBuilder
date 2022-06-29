package uz.abbos.resumebuilder.service;

import org.springframework.stereotype.Service;
import uz.abbos.resumebuilder.dto.ResumeDto;

@Service
public class ResumeServiceImpl  {
    public ResumeDto get(Long id) {
        return null;
    }

    public String create(ResumeDto o) {
        //todo: Check User
        //todo: check Image
        //todo: get list of education_mapping and save to db
        //todo: get list of expirience and save to db
        return null;
    }

    public String update(ResumeDto ResumeDto) {
        return null;
    }

    public String delete(Long id) {
        return null;
    }
}
