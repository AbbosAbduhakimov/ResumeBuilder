package uz.abbos.resumebuilder.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abbos.resumebuilder.dto.EducationDto;
import uz.abbos.resumebuilder.service.EducationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/education")
public class EducationController {

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }
    private final EducationService educationService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getEducation(@PathVariable("id") Long id){
        EducationDto response = educationService.get(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createEducation(@RequestBody @Valid EducationDto educationDto){
        String response = educationService.create(educationDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEducation(@RequestBody @Valid EducationDto educationDto){
        String response = educationService.update(educationDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable("id") Long id){
        String response = educationService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
