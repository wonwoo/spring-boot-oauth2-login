package ml.wonwoo.springbootoauth2login;

import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class LoginController {
  private final InMemoryClientRegistrationRepository clientRegistrationRepository;

  public LoginController(InMemoryClientRegistrationRepository clientRegistrationRepository) {
    this.clientRegistrationRepository = clientRegistrationRepository;
  }

  @GetMapping("/login")
  public String login(Model model) {
    List<Registration> registrations = StreamSupport.stream(clientRegistrationRepository.spliterator(), true)
        .map(clientRegistration -> new Registration(clientRegistration.getRegistrationId(),
            OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
                + "/" + clientRegistration.getRegistrationId(),
            clientRegistration.getClientName())).collect(Collectors.toList());
    model.addAttribute("registrations", registrations);
    return "login";
  }
}
