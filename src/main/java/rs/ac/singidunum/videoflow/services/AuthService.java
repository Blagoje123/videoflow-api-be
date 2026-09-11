package rs.ac.singidunum.videoflow.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.singidunum.videoflow.dto.Dtos.*;
import rs.ac.singidunum.videoflow.entities.User;
import rs.ac.singidunum.videoflow.repositories.UserRepository;
import rs.ac.singidunum.videoflow.security.JwtService;

@Service
public class AuthService {
    private final AuthenticationManager auth;
    private final UserRepository users;
    private final JwtService jwt;

    public AuthService(AuthenticationManager auth, UserRepository users, JwtService jwt) {
        this.auth = auth; this.users = users; this.jwt = jwt;
    }

    public Tokens login(Login body) {
        try { auth.authenticate(new UsernamePasswordAuthenticationToken(body.email(), body.password())); }
        catch (AuthenticationException e) { throw unauthorized(); }
        return tokens(users.findByEmailIgnoreCase(body.email()).orElseThrow(this::unauthorized));
    }

    public Tokens refresh(Refresh body) {
        if (!jwt.validRefresh(body.refreshToken())) throw unauthorized();
        User user = users.findByEmailIgnoreCase(jwt.email(body.refreshToken())).orElseThrow(this::unauthorized);
        return tokens(user);
    }

    private Tokens tokens(User user) {
        return new Tokens(jwt.access(user), jwt.refresh(user), user.getName(), user.getRole());
    }

    private ResponseStatusException unauthorized() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Neispravni podaci ili token");
    }
}
