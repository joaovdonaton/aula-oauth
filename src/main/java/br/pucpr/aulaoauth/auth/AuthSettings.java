package br.pucpr.aulaoauth.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("/auth.properties")
@ConfigurationProperties("auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSettings {
    private String clientId;
    private String clientSecret;
}
