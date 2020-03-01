package com.alpyuktug.covid_19.Services;

public class ApiUtils {

    public static final String BaseURL = "https://alperenyukselaltug.com/";

    public static AppDAOInerface getCountriesDAOInerface()
    {
        return RetrofitClient.getClient(BaseURL).create(AppDAOInerface.class);
    }

}
