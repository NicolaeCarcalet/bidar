package uaic.fii.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    private static final String SECRET_KEY = "SehnsuchtFurSchweiz";
    private static final String ISSUER = "BIDAR";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken() {
        try {
            return JWT
                    .create()
                    .withIssuer(ISSUER)
                    .sign(ALGORITHM);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Couldn't create jwt token");
        }
    }
}
