package com.ed.timemanager.commons.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtil {
    //region Public

    public String generateToken(UUID userId, String secretKey, int lifetime) {

        Date now = new Date();

        return Jwts.builder()
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + lifetime))
            .claim("id", userId.toString())
            .signWith(createPrivateKey(secretKey))
            .compact();
    }

    public UUID getUserIdFromToken(String token, String secretKey) {

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(createPrivateKey(secretKey))
            .build()
            .parseClaimsJws(prepareToken(token))
            .getBody();

        return UUID.fromString(claims.get("id").toString());
    }

    //endregion
    //region Private

    private String prepareToken(String token) {
        
        return token.replace("Bearer ", "");
    }

    private PrivateKey createPrivateKey(String key) {

        byte[] keyBytes = Base64.getDecoder().decode(
            key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "")
        );

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
        catch (InvalidKeySpecException | NoSuchAlgorithmException e) {

            throw new IllegalStateException(
                "Error occurred while creating private key",
                e
            );
        }
    }

    //endregion
}
