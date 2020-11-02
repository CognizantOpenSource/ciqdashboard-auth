package com.cognizant.authapi.collector.model;

/**
 * Created by 784420 on 9/27/2019 6:42 PM
 */
public class BaseConstants {
    private BaseConstants() {}

    public static final String CHART_COLLECTOR = "CHART_COLLECTOR";
    public static final String CHART = "chart";
    public static final String APP_STATUS = "appstatus";
    public static final String USER_DOES_NOT_HAVE_THE_S_ROLE = "Invalid User to generate Collector token (User doesn't have the %s role)";

    /*Token keys*/
    public static final String EXTERNAL_TOKEN = "external_token";
    public static final String TOKEN_EXPIRES_AT = "tokenExpiresAt";
    public static final String TOKEN_ID = "token-id";

}
