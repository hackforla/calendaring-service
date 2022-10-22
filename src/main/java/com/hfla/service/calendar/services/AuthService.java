package com.hfla.service.calendar.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nylas.GoogleProviderSettings;
import com.nylas.HostedAuthentication;
import com.nylas.NativeAuthentication;
import com.nylas.NativeAuthentication.AuthRequestBuilder;
import com.nylas.NylasApplication;
import com.nylas.NylasClient;
import com.nylas.ProviderSettings;
import com.nylas.RequestFailedException;
import com.nylas.Scope;

import okhttp3.HttpUrl;

@Component
public class AuthService {
    @Value("${nylas.client.id}")
    private String clientId;  

    @Value("${nylas.client.secret}")
    private String clientSecret;

    @Value("${nylas.redirect.uri}")
    private String redirectUri;

    @Value("${google.client.id}")
    private String googleClientId;  

    private static Set<Scope> scopes = new HashSet<Scope>() {{
      addAll(Arrays.asList(Scope.values()));
      remove(Scope.EMAIL_METADATA);
    }};

    public String hostedAuthUrlRedirect() {
      NylasClient client = new NylasClient();
      NylasApplication application = client.application(clientId, clientSecret);
      HostedAuthentication authentication = application.hostedAuthentication();
      String url = authentication.urlBuilder()
          .redirectUri(redirectUri)
          .responseType("code")
          .scopes(Scope.CALENDAR, Scope.CALENDAR_READ_ONLY)
          .state("example_csrf_token")
          .buildUrl();

      return "redirect:" + url;
    }

    public String getAccessToken(String code) throws IOException, RequestFailedException {
      NylasClient client = new NylasClient();
      NylasApplication application = client.application(clientId, clientSecret);
      String accessToken = application.hostedAuthentication().fetchToken(code).getAccessToken();
      return accessToken;
    }

    public String nativeAuthRedirect() throws IOException {
      System.out.println(scopes);
      Set<String> googleScopes = GoogleProviderSettings.getMatchingGoogleScopes(scopes);
      // userinfo.email is required for google userinfo endpoint to return email attribute
			// Nylas also appears to require this when it fetches tokeninfo
			googleScopes.add("https://www.googleapis.com/auth/userinfo.email");
			// userinfo.profile is required for google userinfo endpoint to return name attribute
			// this makes the example nicer in fetching the user name from Google and using it with Nylas, but is not
			// required by Nylas if you have the user name from somewhere else.
			googleScopes.add("https://www.googleapis.com/auth/userinfo.profile");

      HttpUrl url = HttpUrl.get("https://accounts.google.com/o/oauth2/v2/auth").newBuilder()
					.addQueryParameter("client_id", googleClientId)
					.addQueryParameter("redirect_uri", redirectUri)
					.addQueryParameter("response_type", "code")
					.addQueryParameter("scope", String.join(" ", googleScopes))
					.addQueryParameter("access_type", "offline")
					// A user prompt is required for Google to grant the refresh_token in addition to the access_token.
					// Google may skip the prompt if the user has  previously granted these scopes to this client.
					// Adding this parameter prompt=consent ensures that the prompt is never skipped.
					.addQueryParameter("prompt", "consent")
					// in a real system, the state parameter should be a unguessable function of the user account
					// or session to prevent CSRF attacks
					.addQueryParameter("state", "EXAMPLE_CSRF_TOKEN")
					.build();
      
      System.out.println(url.toString());

      return "redirect:" + url.toString();
    }

    public void nativeAuth() throws IOException, RequestFailedException {
      NylasClient client = new NylasClient();
      NylasApplication application = client.application(clientId, clientSecret);
      NativeAuthentication authentication = application.nativeAuthentication();
      GoogleProviderSettings googleSettings = ProviderSettings.google()
        .googleClientId("")
        .googleClientSecret("")
        .googleRefreshToken("");

      AuthRequestBuilder authRequest = authentication.authRequest()
        .name("")
        .emailAddress("emailAddress")
        .providerSettings(googleSettings)
        .scopes(Scope.CALENDAR, Scope.CALENDAR_READ_ONLY);

        System.out.println("Making a native authentication request.");
        String authorizationCode = authRequest.execute();
        System.out.println("Success! Authorization code: " + authorizationCode);
    }
}
