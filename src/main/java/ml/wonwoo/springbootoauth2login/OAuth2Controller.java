package ml.wonwoo.springbootoauth2login;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

  @GetMapping("/")
  OAuth2Authentication getAuthentication(OAuth2Authentication authentication) {
    return authentication;
  }
}
