package Week7.Day2.security;

import Week7.Day2.model.Type.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityChain {

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtTools jwtTools;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
                .permitAll());

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/**")
                .hasAuthority(Ruolo.AMMINISTRATORE.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/dispositivi/**")
                .hasAuthority(Ruolo.VENDITORE.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/dispositivi/**")
                .hasAuthority(Ruolo.UTENTE.name()));

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOrigin("http://www.example.com");
        cors.addAllowedMethod(HttpMethod.GET);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("*/**", cors);
        return configurationSource;
    }

}
