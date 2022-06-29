package uz.abbos.resumebuilder.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abbos.resumebuilder.dto.ExperienceDto;
import uz.abbos.resumebuilder.service.ExperienceService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/experience")
public class ExperienceController {


    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    private final ExperienceService experienceService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getExperience(@PathVariable("id") Long id){
        Set<ExperienceDto> response = experienceService.get(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createExperience(@RequestBody  @Valid Set<ExperienceDto> experienceDto){
        String response = experienceService.create(experienceDto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateExperience(@RequestBody @Valid Set<ExperienceDto> experienceDto){
        String response = experienceService.update(experienceDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable("id") Long id){
        String response = experienceService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
