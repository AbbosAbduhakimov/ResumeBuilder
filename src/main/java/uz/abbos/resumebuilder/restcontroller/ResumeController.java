package uz.abbos.resumebuilder.restcontroller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abbos.resumebuilder.dto.ResumeDto;
import uz.abbos.resumebuilder.service.ResumeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/resume")
public class ResumeController {

    private final ResumeService resumeService;


    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getResume(@PathVariable("id") Long id){
        ResumeDto response = resumeService.get(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody @Valid  ResumeDto resumeDto){
        String response = resumeService.create(resumeDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateResume(@RequestBody @Valid ResumeDto resumeDto){
        String response = resumeService.update(resumeDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable("id") Long id){
        String response = resumeService.delete(id);
        return ResponseEntity.ok().body(response);
    }


}
