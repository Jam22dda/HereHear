package com.ssafy.herehear.global.config;

import com.ssafy.herehear.global.exception.CustomAuthenticationEntryPoint;
import com.ssafy.herehear.global.filter.CustomAuthenticationFilter;
import com.ssafy.herehear.global.oauth.handler.CustomOAuth2LoginFailureHandler;
import com.ssafy.herehear.global.oauth.handler.CustomOAuth2LoginSuccessHandler;
import com.ssafy.herehear.global.oauth.service.CustomOAuth2UserService;
import com.ssafy.herehear.global.oauth.service.CustomOidcUserService;
import com.ssafy.herehear.global.util.CustomAuthorityMapper;
import com.ssafy.herehear.global.util.JwtProvider;
import com.ssafy.herehear.member.repository.MemberRepository;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ClientConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomOidcUserService customOidcUserService;

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    private final CustomOAuth2LoginFailureHandler customOAuth2LoginFailureHandler;
    private final CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;

    @Value("${auth.ignored-urls}")
    private String[] ignoredUrls;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 기본 설정 및 Form 비활성화(VER.6.1.X)
        http
                .cors(conf -> conf.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        // Custom Filter(Ver.6.1.x)
        http
                .authorizeHttpRequests(auth -> auth
                        // Endpoints that do NOT require authentication
                        .requestMatchers(ignoredUrls).permitAll()
                        // Any other endpoint needs to be authenticated
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .addFilterBefore(new CustomAuthenticationFilter(jwtProvider, memberRepository), UsernamePasswordAuthenticationFilter.class);

        // OAuth(ver.6.1.x)
        http
                .oauth2Login(oAuth2 -> oAuth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)
                                .oidcUserService(customOidcUserService))
                        .successHandler(customOAuth2LoginSuccessHandler) // 인증 성공
                        .failureHandler(customOAuth2LoginFailureHandler)
                        .permitAll()
                );

        // API 요청
        // http
        // 	.exceptionHandling()
        // 	.authenticationEntryPoint(customAuthenticationEntryPoint) // 인가 실패
        // 	.accessDeniedHandler(customAccessDeniedHandler); // 인가 실패

        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }

    // CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedOrigin("https://k9b202.p.ssafy.io");
        corsConfiguration.addAllowedOrigin("https://localhost:8080");
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedOrigin("http://localhost:5173");

        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
