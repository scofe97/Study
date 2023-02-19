package com.example.security1.config;

import com.example.security1.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다!
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
// secure 어노테이션 활성화, preAuthorize, postAUthorize 활성화
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    // 해당 메서드의 리턴되는 오브젝트를 IOC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    // 도메인별 권한을 처리함, 마지막 anyRequest -> 나머지 모든건 권한이 필요없다는 것
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

        // 로그인 페이지와 기타 로그인 처리 및 성공 실패 처리를 사용하겠다는 의미 입니다.
        http.formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")// /login 주소가 호출이되면 시큐리티가 낚아채서 대신 로그인을 진행해줌
                .defaultSuccessUrl("/");

        http.oauth2Login()
                // 구글 로그인이 완료된 후의 후처리가 필요함. Tip 코드X(엑세스 토큰 + 사용자 프로필 정보 O)
                // 1 코드받기(인증) 2 엑세서토큰(권한) 3.사용자 프로필 정보 가져옴
                // 4-1. 정보를 토대로 회원가입 자동화, 4-2 정보가 부족하다 (이메일, 전화번호 ..) 그런데 집주소가 필요함
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
        return http.build();
    }

    /*
    기존: WebSecurityConfigurerAdapter를 상속하고 configure매소드를 오버라이딩하여 설정하는 방법
    => 현재: SecurityFilterChain을 리턴하는 메소드를 빈에 등록하는 방식(컴포넌트 방식으로 컨테이너가 관리)
    //https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin").access("\"hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
     */
}
