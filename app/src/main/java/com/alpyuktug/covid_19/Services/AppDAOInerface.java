package com.alpyuktug.covid_19.Services;

import com.alpyuktug.covid_19.Models.CountriesList;
import com.alpyuktug.covid_19.Models.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppDAOInerface {

    @GET("api/COVID19/CountriesJSONTR.php")
    Call<CountriesList> GetCountriesListTR();

    @GET("api/COVID19/CountriesJSONEN.php")
    Call<CountriesList> GetCountriesListEN();

    @GET("api/COVID19/NewsJSONTR.php")
    Call<NewsList> GetNewsListTR();

    @GET("api/COVID19/NewsJSONEN.php")
    Call<NewsList> GetNewsListtEN();
}
