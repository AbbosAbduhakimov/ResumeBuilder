package uz.abbos.resumebuilder.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abbos.resumebuilder.dto.UserDto;
import uz.abbos.resumebuilder.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") Long id){
        UserDto response =  userService.get(id);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto){
        String response = userService.create(userDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto){
        String response = userService.update(userDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        String response = userService.delete(id);
        return ResponseEntity.ok().body(response);
    }

}
