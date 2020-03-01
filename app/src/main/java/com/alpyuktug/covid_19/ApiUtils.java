package com.alpyuktug.covid_19;

public class ApiUtils {

    public static final String BaseURL = "https://alperenyukselaltug.com/";

    public static  CountriesDAOInerface getCountriesDAOInerface()
    {
        return RetrofitClient.getClient(BaseURL).create(CountriesDAOInerface.class);
    }

}
