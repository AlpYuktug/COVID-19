package com.alpyuktug.covid_19.Services;

import com.alpyuktug.covid_19.Models.CountriesList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesDAOInerface {

    @GET("api/COVID19/CountriesJSONTR.php")
    Call<CountriesList> GetCountriesListTR();

    @GET("api/COVID19/CountriesJSONEN.php")
    Call<CountriesList> GetCountriesListEN();
}
