package in.main.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.main.dto.auth.LoginRequest;
import in.main.dto.auth.LoginResponse;
import in.main.service.JwtTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenService jwtService;

	@PostMapping
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

		String token = jwtService.generateToken(request.getEmail());

		return LoginResponse.builder().token(token).email(request.getEmail()).build();
	}
}