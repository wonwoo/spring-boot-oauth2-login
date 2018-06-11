package ml.wonwoo.springbootoauth2login;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
  private final InMemoryClientRegistrationRepository clientRegistrationRepository;

  public LoginController(InMemoryClientRegistrationRepository clientRegistrationRepository) {
    this.clientRegistrationRepository = clientRegistrationRepository;
  }

  @GetMapping("/login")
  public String login(Model model) {
    List<Registration> registrations = new ArrayList<>();
    for (ClientRegistration clientRegistration : clientRegistrationRepository) {
      registrations.add(new Registration(clientRegistration.getRegistrationId(),
          OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
              + "/" + clientRegistration.getRegistrationId(),
          clientRegistration.getClientName()));
    }
    model.addAttribute("registrations", registrations);
    return "login";
  }
}
