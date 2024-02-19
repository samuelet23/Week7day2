package Week7.Day1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityChain {

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtTools jwtTools;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
                .permitAll());

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/dipendenti/**")
                .denyAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/dispositivi/**")
                .denyAll());

        return httpSecurity.build();
    }

}
