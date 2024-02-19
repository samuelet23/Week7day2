package Week7.Day1.security;

import Week7.Day1.model.Dipendente.Dipendente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@PropertySource("application.properties")
public class JwtTools {

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value(("${spring.jwt.expiration}"))
    private String durata;

    public String creaToken(Dipendente dipendente){
        long currentTimeMill = System.currentTimeMillis();
        return Jwts.builder()
                .subject(dipendente.getUsername())
                .issuedAt(new Date(currentTimeMill))
                .expiration(new Date(currentTimeMill+Long.parseLong(durata)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public void validaToken(String token) throws Exception {
        try{
        Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public String extractUsername(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        
    }
}
