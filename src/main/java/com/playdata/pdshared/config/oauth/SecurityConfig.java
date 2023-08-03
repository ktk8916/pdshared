package com.playdata.pdshared.config.oauth;


import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
//
//
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
//
//    @Bean
//    @Order(1)
//    //OAuth 2.0 인증 서버의 보안 필터 체인을 구성하는 데 사용(클라이언트 인증 관리, 액세스 토큰 발급하는 서버)
//    //인증 서버를 설정하고 보호하는 데 사용된다(좀 어렵다 인증서버까지 이번프로젝트에선 다루지 말자)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
//        http
//                // Redirect to the login page when not authenticated from the
//                // authorization endpoint
//                .exceptionHandling((exceptions) -> exceptions
//                        .defaultAuthenticationEntryPointFor(//인증되지 않은 사용자 접근시 로그인 페이지로 리디렉션된다
//                                //인증되지 않은 상태에서 유저가 접근시 로그인으로 리디렉션하는 엔트리 포인트 설정
//                                new LoginUrlAuthenticationEntryPoint("/login"),
//                                //리디렉션을 어떤 요청 유형에서 처리할지를 설정(뭘로하지 일단 all)
//                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
//                        )
//                )
//                // Accept access tokens for User Info and/or Client Registration
//                .oauth2ResourceServer((resourceServer) -> resourceServer
//                        .jwt(Customizer.withDefaults()));
//
//        return http.build();
//    }

    @Bean
//    @Order(2)
    //웹 어플리케이션의 보안 설정을 다룬다.(client측 서버 필터다 이걸 주로 사용하자)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                //모든 요청을 인증이 필요한 요철으로 보낸다-> 인증 안받은 사용자 다 로그인 ㄱㄱ
                .authorizeHttpRequests((authorize) -> authorize
                        //어떤 요청이든 인증돼야함
                        .anyRequest().permitAll()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
//
//    @Bean
//    //사용자의 인증 정보를 가져오는 인터페이스 설정
//    public UserDetailsService userDetailsService() {
//        //spring security5 이전 방식, 사용자의 비밀번호를 인코딩하는 방법을 정의
//        //사실 5 이후에는 보안을 강화하기 위해 다른 인코딩 방식이 권장되므로 이번에는 패스하는게 좋을듯?
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                //보안 이슈나는 곳(비밀번호를 넣고 인코딩? 이건 좀;)
//                .password("password")
//                //유저  역할 정의
//                .roles("USER")
//                .build();
//        // 여기가 좀 핵심인데 userdetails객체를 구현체인 InMemoryUserDetailsManager에 담아서 반환
//        // 인메모리 방식으로 사용자를 관리함. 즉 사용자 정보가 메모리에 저장되며, 실제 데이터베이스의 사용자 정보를
//        // 저장하지 않고도 간단한 인증 테스트 가능
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//    @Bean
//    //클라이언트를 저장하고 관리하는 인터페이스 설정
//    public RegisteredClientRepository registeredClientRepository() {
//        //등록된 클라이언트를 생성하고 uuid형식으로 id설정
//        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                //oauth2.0 클라이언트의 고유한 id
//                .clientId("oidc-client")
//                //암호화 없이 비밀번호를 저장함을 의미->실제 환경에서 이러면 바로 고소각
//                .clientSecret("{noop}secret")
//                //클라이언트의 인증 방법 설정->id랑 비밀번호를 http기본 인증 헤더로 전달하는 방식
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                //클라이언트가 사용할 수 있는 인가 그랜트 타입 설정(클라이언트가 엑세스 토큰을 요청할 때 사용하는 인증 방식)
//                //authrization code, refresh token 두가지로 설정 전좌는 잘 모름 공부해야함
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                //인증이 성공하면 사용자가 이 url로 리디렉션되어야함-> 이건 직접 발급받아야 하는 부분?
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//                //로그아웃하면 가지는 부분->login페이지로 ㄱㄱ?
//                .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                //요청 범위 설정함-> 리로스 서버에서 머 가져올거냐
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                //클라이언트가 사용자의 인가 동의를 요구하는 설정 -> 예를들어 카카오에서 요청 수락이 되겠지?
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        return new InMemoryRegisteredClientRepository(oidcClient);
//    }
//
//    @Bean
//    //JsonWebKey 제공하는 인터페이스
//    public JWKSource<SecurityContext> jwkSource() {
//        KeyPair keyPair = generateRsaKey();
//        //공개키 반환
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        //개인키 반환
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        //RSA키 생성
//        RSAKey rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<SecurityContext>(jwkSet);
//    }
//
//    private static KeyPair generateRsaKey() {
//        KeyPair keyPair;
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            keyPair = keyPairGenerator.generateKeyPair();
//        }
//        catch (Exception ex) {
//            throw new IllegalStateException(ex);
//        }
//        return keyPair;
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        KeyPair keyPair = generateRsaKey();
//        //공개키 반환
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        //개인키 반환
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        //RSA키 생성
//        RSAKey rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//
//        return OAuth2AuthorizationServerConfiguration.jwtDecoder(new ImmutableJWKSet<SecurityContext>(jwkSet));
//    }
//
//
//    @Bean
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder().build();
//    }
//
//}