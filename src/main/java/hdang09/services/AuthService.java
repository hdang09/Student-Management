package hdang09.services;

import hdang09.enums.ResponseStatus;
import hdang09.dtos.requests.LoginDTO;
import hdang09.dtos.responses.LoginResponseDTO;
import hdang09.models.Response;
import hdang09.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @Autowired
    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<Response<LoginResponseDTO>> login(LoginDTO account) {
        // Check username and password
        if (!account.getUsername().equals(username) || !account.getPassword().equals(password)) {
            Response<LoginResponseDTO> response = new Response<>(ResponseStatus.ERROR, "Username or password is not correct");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Generate token
        String accessToken = jwtUtil.generateAccessToken(account.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(account.getUsername());
        LoginResponseDTO tokenDTO = new LoginResponseDTO(accessToken, refreshToken);

        // Return response
        Response<LoginResponseDTO> response = new Response<>(ResponseStatus.SUCCESS, "Login successfully", tokenDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
