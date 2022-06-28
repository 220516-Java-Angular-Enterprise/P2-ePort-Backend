package com.revature.ePort.auth;



import com.revature.ePort.auth.dtos.response.Principal;
import com.revature.ePort.user.User;
import com.revature.ePort.user.UserService;
import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.AuthenticationException;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import com.revature.ePort.util.custom_exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Inject
    private JwtConfig jwtConfig;
    private UserService userService;

    public TokenService() {
        super();
    }

    @Inject
    @Autowired
    public TokenService(JwtConfig jwtConfig, UserService userService) {
        this.jwtConfig = jwtConfig;
        this.userService = userService;
    }






    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setIssuer("yolp")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUsername())
                .claim("role", subject.getRole())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return new Principal(claims.getId(), claims.getSubject(), claims.get("role", String.class));
        } catch (Exception e) {
            return null;
        }
    }

    public Principal noTokenThrow(String token){
        Principal requester = extractRequesterDetails(token);
        if(requester == null) throw new UnauthorizedException("No authorization found");//401
        User user = userService.getUserByUsername(requester.getUsername());
        if(user == null) throw new InvalidRequestException("Error cannot find user");//404
        if(!user.isActive()) throw new AuthenticationException("Inactive user");//403
        return requester;
    }
}
