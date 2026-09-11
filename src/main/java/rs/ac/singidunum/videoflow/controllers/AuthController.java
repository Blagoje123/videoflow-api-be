package rs.ac.singidunum.videoflow.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.videoflow.dto.Dtos.*;
import rs.ac.singidunum.videoflow.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service) { this.service = service; }
    @PostMapping("/login") public Tokens login(@Valid @RequestBody Login body) { return service.login(body); }
    @PostMapping("/refresh") public Tokens refresh(@Valid @RequestBody Refresh body) { return service.refresh(body); }
}
