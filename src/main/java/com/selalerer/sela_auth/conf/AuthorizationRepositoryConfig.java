package com.selalerer.sela_auth.conf;

import com.selalerer.sela_auth.repository.AuthorizationRepository;
import com.selalerer.sela_auth.repository.InMemoryAuthorizationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationRepositoryConfig {
    @Bean
    public AuthorizationRepository authorizationRepository(
            @Value("${sela_auth.repo_type:in_mem}") String repoType) {
        return switch (repoType) {
            case "in_mem" -> new InMemoryAuthorizationRepository();
            default -> throw new RuntimeException("sela_auth.repo_type=" + repoType +
                    " is not supported.");
        };
    }
}
