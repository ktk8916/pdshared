package com.playdata.pdshared.config.token;
//
//
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//@Aspect
//@RequiredArgsConstructor
public class TokenAspect {
}

//    private final TokenService tokenService;
//    @Before("@annotation(TokenRequired)")
//    public void TokenCheck(){
//        //Todo 토큰 없으면 로그인 페이지로 가게함
//        ServletRequestAttributes request =
//                (ServletRequestAttributes) RequestContextHolder
//                        .currentRequestAttributes();
//        String token = request.getRequest().getHeader("Authorization");
//        if(token== null) throw new RuntimeException("token is null");
//        token = token.replace("Bearer ","");
//        System.out.println(authService.getClaims(token));
//
//
//    }
//
//
//
//
//}
