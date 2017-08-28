package io.gary.bestbook.book.config;

import feign.RequestInterceptor;
import io.gary.bestbook.book.config.feign.OAuth2FeignRequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2FeignConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public OAuth2RestTemplate oAuth2FeignRestTemplate(OAuth2ProtectedResourceDetails clientCredentialsResourceDetails) {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails, new DefaultOAuth2ClientContext());
    }

    @Bean
    public RequestInterceptor oAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2FeignRestTemplate) {
        return new OAuth2FeignRequestInterceptor(oAuth2FeignRestTemplate);
    }
}
