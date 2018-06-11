package ml.wonwoo.springbootoauth2login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessTokenController {

  private final OAuth2AuthorizedClientService clientService;

  public AccessTokenController(OAuth2AuthorizedClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/")
  public String accessToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
      OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
      String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
      OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
      return client.getAccessToken().getTokenValue();
    }
    return null;
  }

  @GetMapping("/user")
  public OAuth2User index(@AuthenticationPrincipal OAuth2User oauth2User) {
    return oauth2User;
  }
}
