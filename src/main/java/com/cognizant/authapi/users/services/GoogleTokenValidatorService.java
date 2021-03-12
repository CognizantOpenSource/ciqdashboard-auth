//package com.cognizant.authapi.users.services;
//
//import com.cognizant.authapi.base.error.InvalidValueException;
//import com.cognizant.authapi.users.beans.User;
//import com.cognizant.authapi.users.util.UserUtil;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//
///**
// * Created by 784420 on 7/19/2019 1:14 PM
// */
//@Service
//@Slf4j
//public class GoogleTokenValidatorService {
//
//    @Autowired
//    HttpTransport httpTransport;
//    @Autowired
//    private UserUtil userUtil;
//    @Value("${app.oauth.google.client.id}")
//    private String googleOauthClientId;
//
//    public User validateGoogleToken(String idTokenString) {
//        JacksonFactory jsonFactory = new JacksonFactory();
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
//                .setAudience(Collections.singletonList(googleOauthClientId))
//                .build();
//
//        GoogleIdToken idToken = null;
//        User user = null;
//        try {
//            idToken = verifier.verify(idTokenString);
//        } catch (GeneralSecurityException | IllegalArgumentException | IOException e) {
//            throw new InvalidValueException("tokenId", e);
//        }
//        if (idToken != null) {
//            GoogleIdToken.Payload payload = idToken.getPayload();
//            user = userUtil.convertPayloadToUser(payload);
//        } else {
//            throw new InvalidValueException("tokenId");
//        }
//
//        return user;
//    }
//}
