package org.example.soap.services;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.soap.config.EnvConfig;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@WebService(serviceName = "Auth")
public class Auth {

    @WebMethod(operationName = "getToken")
    public String getToken() {
     var secretToken = EnvConfig.getInstance().getSecretToken();
        Key key = Keys.hmacShaKeyFor(secretToken.getBytes(StandardCharsets.UTF_8));

        // Construye el token JWT
        return Jwts.builder()
                .setSubject("exampleUser") // Identificador del token (puede ser el nombre de usuario, ID, etc.)
                .setIssuer("your-app") // Emisor del token
                .setIssuedAt(new Date()) // Fecha de emisión
                .claim("role", "ADMIN") // Claims personalizados
                .signWith(key, SignatureAlgorithm.HS256) // Firma con la clave secreta
                .compact();
    }

}
