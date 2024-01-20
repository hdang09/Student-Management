package hdang09.controllers;

import hdang09.dtos.requests.LoginDTO;
import hdang09.dtos.responses.TokenDTO;
import hdang09.models.Response;
import hdang09.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<Response<TokenDTO>> login(@Valid @RequestBody LoginDTO account) {
        return authService.login(account);
    }
}