package uz.abbos.resumebuilder.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abbos.resumebuilder.dto.CompanyDto;
import uz.abbos.resumebuilder.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    private final CompanyService companyService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable("id") Long id){
        CompanyDto response = companyService.get(id);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody @Valid CompanyDto companyDto){
        String response = companyService.create(companyDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@RequestBody @Valid CompanyDto companyDto){
        String response = companyService.update(companyDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id){
        String response = companyService.delete(id);
        return ResponseEntity.ok().body(response);
    }
}

