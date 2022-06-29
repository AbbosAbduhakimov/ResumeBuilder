package uz.abbos.resumebuilder.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abbos.resumebuilder.dto.EducationMappingDto;
import uz.abbos.resumebuilder.service.EducationMappingService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/edu-mapping")
public class EducationMappingController {

    public EducationMappingController(EducationMappingService educationMappingService) {
        this.educationMappingService = educationMappingService;
    }

    private final EducationMappingService educationMappingService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getEduMapping(@PathVariable("id") Long id){
        EducationMappingDto response = educationMappingService.get(id);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    public ResponseEntity<?> createEduMapping(@RequestBody @Valid Set<EducationMappingDto> educationMappingDto){
        String response = educationMappingService.create(educationMappingDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEduMapping(@RequestBody @Valid Set<EducationMappingDto> educationMappingDto){
        String response = educationMappingService.update(educationMappingDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEduMapping(@PathVariable("id") Long id){
        String response = educationMappingService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}
