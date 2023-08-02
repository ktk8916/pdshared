package com.playdata.pdshared.config.token;

import com.playdata.pdshared.domain.member.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    public String makeToken(Member member){
        SecretKeySpec key = getKey();
        String compact = Jwts.builder()
                .claim("providerId", member.getProviderId())
                .setExpiration(new Date(System.currentTimeMillis() + 120_000))
                .signWith(key)
                .compact();
        return compact;
    }
    public Map<String, Object> getClaims(String token){
//        SecretKeySpec key = getKey();
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parse(token)
                .getBody();
    }


    private SecretKeySpec getKey() {
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), hs256.getJcaName());
        return key;
    }
}
