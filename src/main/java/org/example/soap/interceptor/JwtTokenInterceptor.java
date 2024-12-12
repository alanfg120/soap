package org.example.soap.interceptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.example.soap.config.EnvConfig;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.Map;


public class JwtTokenInterceptor extends AbstractSoapInterceptor {



    public JwtTokenInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }



    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        Map<String, List<String>> authHeader = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);

        if (authHeader != null && authHeader.containsKey("authorization") && authHeader.get("authorization").get(0) != null && authHeader.get("authorization").get(0).contains("Bearer")) {
            String token = authHeader.get("authorization").get(0).substring(7);

            try {
                Key key = Keys.hmacShaKeyFor(EnvConfig.getInstance().getSecretToken().getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                // Si es necesario, almacena los claims en el contexto
                message.getExchange().put("claims", claims);

            } catch (Exception e) {
                throw new Fault(new SecurityException("Token JWT no válido"));
            }
        } else {
            throw new Fault(new SecurityException("Token JWT faltante o malformado"));
        }
    }
}
