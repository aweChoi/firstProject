package org.example.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console화면 사용을 위해 해당 옵션 disable
                .and()
                .authorizeRequests() //URL별 권한 관리를 설정하는 옵션 시작점(authorizeRequests가 선언되어야 antMatchers옵션 사용 가능)
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() //전체열람권한
                .antMatchers("/api/v1/**") //User권한만 가능
                .hasRole(Role.USER.name()) //권한 관리 대장 지정 옵션(URL, HTTP메소드별 관리 가능)
                .anyRequest().authenticated() //설정된 값들 이외 나머지 URL들을 나타냄. authenticated를 추가하여 나머지 URL은 로그인된 사용자만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃시 진입
                .and()
                .oauth2Login() //로그인 기능에 대한 여러 설정 진입점
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올때 설정들 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공시 후속 조치를 진행할 UserService인터페이스의 구현체를 등록. 사용자정보 가져온 후 추가진행기능명시할 수 있다.
    }
}
