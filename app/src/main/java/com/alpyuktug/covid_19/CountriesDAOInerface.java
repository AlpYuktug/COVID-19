package com.alpyuktug.covid_19;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesDAOInerface {

    @GET("api/COVID19/CountriesJSONTR.php")
    Call<CountriesList> GetCountriesList();
}
