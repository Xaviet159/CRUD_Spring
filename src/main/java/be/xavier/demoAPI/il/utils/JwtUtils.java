package be.xavier.demoAPI.il.utils;

import be.xavier.demoAPI.dl.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final JwtBuilder builder;
    private final JwtParser parser;

    private final byte[] secret = "Yabadabadoooooooooooooooooooooooooooooooooooo".getBytes(StandardCharsets.UTF_8);
    private final SecretKey secretKey = new SecretKeySpec(secret, "HmacSHA256");
    private final int expireAt = 86400; // 1Day

    public JwtUtils() {

        this.builder = Jwts.builder().signWith(secretKey);
        this.parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String generateToken(User u) {

        return builder
                .setSubject(u.getUsername())
                .claim("id",u.getId())
                .claim("role",u.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireAt * 1000L))
                .compact();
    }

    public Claims getClaims(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}