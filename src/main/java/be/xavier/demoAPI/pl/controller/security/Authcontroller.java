package be.xavier.demoAPI.pl.controller.security;

import be.xavier.demoAPI.bll.services.security.IAuthService;
import be.xavier.demoAPI.il.utils.JwtUtils;
import be.xavier.demoAPI.pl.models.user.login.UserLoginForm;
import be.xavier.demoAPI.pl.models.user.login.UserRegisterForm;
import be.xavier.demoAPI.pl.models.user.login.UserTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import be.xavier.demoAPI.dl.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Authcontroller {

    private final IAuthService IAuthService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserTokenDTO> register(@RequestBody UserRegisterForm form) {
        User u = IAuthService.register(form.toEntity());
        return ResponseEntity.ok(mapUserToken(u));
    }

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserTokenDTO> login(@RequestBody UserLoginForm form) {
        User u = IAuthService.login(form.username(), form.password());
        return ResponseEntity.ok(mapUserToken(u));
    }

    private UserTokenDTO mapUserToken(User u) {

        String token = jwtUtils.generateToken(u);
        return UserTokenDTO.fromEntity(u,token);
    }
}
