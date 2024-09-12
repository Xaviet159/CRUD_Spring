package be.xavier.demoAPI.pl.controller;

import be.xavier.demoAPI.bll.services.iservices.IUserService;
import be.xavier.demoAPI.pl.models.user.UserDetailDTO;
import be.xavier.demoAPI.pl.models.user.UserForm;
import be.xavier.demoAPI.pl.models.user.UserShortDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService IUserService;

    @GetMapping("/list")
    public ResponseEntity<List<UserShortDTO>> getAllUser(){
        List<UserShortDTO> users = IUserService.getAll().stream()
                .map(UserShortDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id:^\\d+}")
    public ResponseEntity<UserDetailDTO> getUser(@PathVariable long id){
        UserDetailDTO user = UserDetailDTO.fromEntity(IUserService.getById(id));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<UserShortDTO> createUser(@RequestBody UserForm form){
        Long id = IUserService.create(form.toEntity());
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PostMapping("/add-multiple")
    public ResponseEntity<?> createMultipleUsers(@RequestBody List<UserForm> forms) {
        List<Long> createdUserIds = forms.stream()
                .map(userForm -> IUserService.create(userForm.toEntity()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(createdUserIds);
    }

    @DeleteMapping("/{id:^\\d+}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        IUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id:^\\d+}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @Valid @RequestBody UserForm form){
        IUserService.update(id, form.toEntity());
        return ResponseEntity.noContent().build();
    }

}
