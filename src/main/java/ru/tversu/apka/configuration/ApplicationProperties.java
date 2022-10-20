package ru.tversu.apka.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
@ConfigurationProperties(prefix = "application.apka", ignoreUnknownFields = false)
public class ApplicationProperties {

  private final Security security = new Security();
  private final CorsConfiguration cors = new CorsConfiguration();
  private MimeTypesFilter mimeTypesFilter;
  private String webChatUrl;
  private boolean securedViaToken;

  public CorsConfiguration getCors() {
    return cors;
  }

  public static class Security {

    private final ClientAuthorization clientAuthorization = new ClientAuthorization();
    private final Authentication authentication = new Authentication();
    private final RememberMe rememberMe = new RememberMe();
    private final OAuth2 oauth2 = new OAuth2();
    private String contentSecurityPolicy = ApplicationDefaults.Security.contentSecurityPolicy;

    public ClientAuthorization getClientAuthorization() {
      return clientAuthorization;
    }

    public Authentication getAuthentication() {
      return authentication;
    }

    public RememberMe getRememberMe() {
      return rememberMe;
    }

    public OAuth2 getOauth2() {
      return oauth2;
    }

    public String getContentSecurityPolicy() {
      return contentSecurityPolicy;
    }


    public void setContentSecurityPolicy(String contentSecurityPolicy) {
      this.contentSecurityPolicy = contentSecurityPolicy;
    }

    public static class ClientAuthorization {

      private String accessTokenUri =
          ApplicationDefaults.Security.ClientAuthorization.accessTokenUri;

      private String tokenServiceId =
          ApplicationDefaults.Security.ClientAuthorization.tokenServiceId;

      private String clientId = ApplicationDefaults.Security.ClientAuthorization.clientId;

      private String clientSecret = ApplicationDefaults.Security.ClientAuthorization.clientSecret;

      public String getAccessTokenUri() {
        return accessTokenUri;
      }

      public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
      }

      public String getTokenServiceId() {
        return tokenServiceId;
      }

      public void setTokenServiceId(String tokenServiceId) {
        this.tokenServiceId = tokenServiceId;
      }

      public String getClientId() {
        return clientId;
      }

      public void setClientId(String clientId) {
        this.clientId = clientId;
      }

      public String getClientSecret() {
        return clientSecret;
      }

      public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
      }
    }

    public static class Authentication {

      private final Jwt jwt = new Jwt();

      public Jwt getJwt() {
        return jwt;
      }

      public static class Jwt {

        private String secret = ApplicationDefaults.Security.Authentication.Jwt.secret;

        private String base64Secret = ApplicationDefaults.Security.Authentication.Jwt.base64Secret;

        private long tokenValidityInSeconds = ApplicationDefaults.Security.Authentication.Jwt
            .tokenValidityInSeconds;

        private long tokenValidityInSecondsForRememberMe =
            ApplicationDefaults.Security.Authentication.Jwt
                .tokenValidityInSecondsForRememberMe;

        public String getSecret() {
          return secret;
        }

        public void setSecret(String secret) {
          this.secret = secret;
        }

        public String getBase64Secret() {
          return base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
          this.base64Secret = base64Secret;
        }

        public long getTokenValidityInSeconds() {
          return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
          this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public long getTokenValidityInSecondsForRememberMe() {
          return tokenValidityInSecondsForRememberMe;
        }

        public void setTokenValidityInSecondsForRememberMe(
            long tokenValidityInSecondsForRememberMe) {
          this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }
      }
    }

    public static class RememberMe {

      @NotNull
      private String key = ApplicationDefaults.Security.RememberMe.key;

      public String getKey() {
        return key;
      }

      public void setKey(String key) {
        this.key = key;
      }
    }

    public static class OAuth2 {
      private List<String> audience = new ArrayList<>();

      public List<String> getAudience() {
        return Collections.unmodifiableList(audience);
      }

      public void setAudience(@NotNull List<String> audience) {
        this.audience.addAll(audience);
      }
    }
  }

  @Data
  public static class MimeTypesFilter {
    private List<String> allowed;
  }
}