package Week7.Day2.security;

import Week7.Day2.Exception.UnAuthorizedException;
import Week7.Day2.model.Dipendente.Dipendente;
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

    //metodo per identificare l'utente nel token(verrà usato quando si logga)
    public String createToken(Dipendente dipendente) {
        long currentTimeMillis = System.currentTimeMillis();// Ottieni il timestamp corrente in millisecondi
        return Jwts.builder()
                .subject(dipendente.getUsername()) // imposta il soggetto del token con lo username dell'utente
                .issuedAt(new Date(currentTimeMillis)) //imposta il timestamp di emissione del token al tempo corrente
                .expiration(new Date(currentTimeMillis + Long.parseLong(durata))) //Imposta la scadenza del token aggiugendo la durata specifica alla data corrente
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))) //Firma il toke utilizzando una chiave HMAC-SHA presa dalla variabile "secret" presente nell'application properties
                .compact(); //compatta il token in una stringa
    }

    //metodo che controlla la validità del token(verrà usato quando si riceverà una richiesta di servizio con un token all'interno)
    public void validaToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))// controlliamo se il token è valido tramite la chiave segreta confrontando gli hash
                    .build()
                    .parse(token);
        } catch (Exception e) {
            throw new UnAuthorizedException(e.getMessage());
        }
    }

    // metodo per estrarre lo username dal Token
    public String extractUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))) //verifica la firma del token JWT tramite la chiave segreta inserita
                .build()// crea un istanza del parser JWT
                .parseSignedClaims(token)// decripta il token
                .getPayload()//recupera il payload del token
                .getSubject();// recupera il soggetto del token

    }
}
