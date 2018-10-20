package com.apibucket.emailservice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.apibucket.emailservice.security.pojo.JwtUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


/**
 * @author vinoth
 *
 */
@Component
public class JwtTokenValidator {

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Tries to parse specified String as a JWT token. If successful, returns User
	 * object with username, id and role prefilled (extracted from token). If
	 * unsuccessful (token is invalid or not containing all required user
	 * properties), simply returns null.
	 *
	 * @param token
	 *            the JWT token to parse
	 * @return the User object extracted from specified token or null if a token is
	 *         invalid.
	 */
	public JwtUserDto parseToken(String token) {
        JwtUserDto u = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            u = new JwtUserDto();
            u.setUsername(body.getSubject());
            u.setRole((String) body.get("role"));
//            System.out.println("Name: "+u.getUsername());
//            System.out.println("ROLE: "+u.getRole());
//            System.out.println("ROle: "+body.get("role"));
        } catch (JwtException e) {
            // Simply print the exception and null will be returned for the userDto
            e.printStackTrace();
        }
        return u;
    }
}
