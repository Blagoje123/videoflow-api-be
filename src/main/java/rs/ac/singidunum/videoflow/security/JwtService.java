package rs.ac.singidunum.videoflow.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.videoflow.entities.User;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final Key key;
    private final long accessMs;
    private final long refreshMs;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.access-ms}") long accessMs,
                      @Value("${app.jwt.refresh-ms}") long refreshMs) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessMs = accessMs;
        this.refreshMs = refreshMs;
    }

    public String access(User user) { return token(user, "access", accessMs); }
    public String refresh(User user) { return token(user, "refresh", refreshMs); }
    public String email(String token) { return claims(token).getSubject(); }

    public boolean validAccess(String token, String email) {
        return valid(token, email, "access");
    }

    public boolean validRefresh(String token) {
        try { return "refresh".equals(claims(token).get("type", String.class)); }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }

    private boolean valid(String token, String email, String type) {
        try {
            Claims c = claims(token);
            return email.equals(c.getSubject()) && type.equals(c.get("type", String.class));
        } catch (JwtException | IllegalArgumentException e) { return false; }
    }

    private String token(User user, String type, long duration) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getEmail()).claim("role", user.getRole()).claim("type", type)
                .setIssuedAt(now).setExpiration(new Date(now.getTime() + duration))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    private Claims claims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }
}
