package com.playdata.pdshared.config.token;
//
//import com.playdata.pdshared.domain.member.domain.entity.Member;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Date;
//import java.util.Map;
//
//@Service
public class TokenService {}
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    public String makeToken(Member member){
//        SecretKeySpec key = getKey();
//        String compact = Jwts.builder()
//                .claim("name", member.getName())
//                .setExpiration(new Date(System.currentTimeMillis() + 120_000))
//                .signWith(key)
//                .compact();
//        return compact;
//    }
//    public Map<String, Object> getClaims(String token){
////        SecretKeySpec key = getKey();
//
//        return (Claims) Jwts.parserBuilder()
//                .setSigningKey(secretKey.getBytes())
//                .build()
//                .parse(token)
//                .getBody();
//    }
//    public MemberDto tokenToMemberDto(String token){
//        Map<String, Object> tokenToData = getClaims(token);
//        MemberDto memberDto = new MemberDto((Long) tokenToData.get("memberId"),  null, (String) tokenToData.get("name"), (Integer) tokenToData.get("age"));
//        return memberDto;
//    }
//
//    private SecretKeySpec getKey() {
//        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
//        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), hs256.getJcaName());
//        return key;
//    }
//}
