package com.cognizant.authapi.base.beans;

public class JwtSecurityConstants {

    private JwtSecurityConstants() {
    }

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String AUTH_TOKEN = "auth_token";
    public static final String EXPIRES_AT = "expiresAt";
    public static final String COLLECTOR_TOKEN = "collector_token";
    public static final String EXTERNAL_TOKEN = "external_token";
    public static final String TOKEN_EXPIRES_AT = "tokenExpiresAt";

    /*Custom Token Constants*/
    public static final String CUSTOM_TOKEN_ID = "X-ciqdashboard-token-id";
    public static final String BEARER_TEMPLATE = "Bearer %s";
}