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
    //region Constants
    
    private final long TEN_DAYS = 864000000;

    //endregion
    //region Public

    public String generateToken(UUID userId, String secretKey) {

        Date now = new Date();

        return Jwts.builder()
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + JwtUtil.TEN_DAYS))
            .claim("id", userId.toString())
            .signWith(createPrivateKey(secretKey))
            .compact();
    }

    public UUID getUserIdFromToken(String token, String secretKey) {

        Claims claims = Jwts.parserBuilder()
            .setSigningKey(createPrivateKey(secretKey))
            .build()
            .parseClaimsJwt(token)
            .getBody();

        return UUID.fromString(claims.get("id").toString());
    }

    //endregion
    //region Private

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
