package com.hfla.service.calendar.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nylas.RequestFailedException;
import com.nylas.Scope;

@Component
public class AuthService {


    private static Set<Scope> scopes = new HashSet<Scope>() {{
      addAll(Arrays.asList(Scope.values()));
      remove(Scope.EMAIL_METADATA);
    }};

    public String hostedAuthUrlRedirect() {
    return "";
    }

    public String getAccessToken(String code) throws IOException, RequestFailedException {

      return "";
    }

    public String nativeAuthRedirect() throws IOException {
    return "";
    }

    public void nativeAuth() throws IOException, RequestFailedException {
        return;
    }
}
