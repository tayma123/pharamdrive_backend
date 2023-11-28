package login.services;


import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate;

    public CustomOAuth2UserService() {
        this.delegate = new DefaultOAuth2UserService();
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String email = null;
        Map<String, Object> attributes = oauth2User.getAttributes();
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            email = (String) attributes.get("email");

        }
        System.out.println("_____________"+email);
        // Enregistrez l'adresse e-mail de l'utilisateur dans votre application

        return oauth2User;
    }
}

