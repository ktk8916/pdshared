package com.playdata.pdshared.config.service;
//import com.playdata.pdshared.config.controller.AccessTokenResponse;
import com.playdata.pdshared.config.token.TokenService;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import com.playdata.pdshared.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public String getGoogleAccessToken(String code) {
        String googleClientId = "77383403490-8ule1ove2rakrbsun74q8mbjnh553mei.apps.googleusercontent.com";
//    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
        String googleClientSecret = "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";
        String GOOGLE_ACCESS_TOKEN_URL = "https://oauth2.googleapis.com/token";
        WebClient webClient = WebClient.builder()
                .baseUrl(GOOGLE_ACCESS_TOKEN_URL)
                .build();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", googleClientId);
        map.add("client_secret", googleClientSecret);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", "http://localhost:8080/login/oauth/code/google");
        AccessTokenResponse accessTokenResponse = webClient.post()
                .bodyValue(map)
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .block();
        return accessTokenResponse.getAccess_token();
    }


    public Map getGoogleUserInfo(String token) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.googleapis.com/oauth2/v2/userinfo")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
        Map map = webClient.get()
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return map;
    }

    public String Signin(Map googleUserInfo){
        Optional<Member> member1=memberRepository.findMemberByProviderId((String)googleUserInfo.get("id"));
        Member member = member1.orElse(new Member(null,(String) googleUserInfo.get("id"),(String) googleUserInfo.get("name"),null,null,null));
        if (member.getId()==null){
            memberRepository.save(new Member(null,(String) googleUserInfo.get("id"),(String) googleUserInfo.get("name"),null,null,null));
        } else{

        }
        return tokenService.makeToken(member);
    }
}
