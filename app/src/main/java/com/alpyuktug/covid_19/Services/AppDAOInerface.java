package com.alpyuktug.covid_19.Services;

import com.alpyuktug.covid_19.Models.CountriesList;
import com.alpyuktug.covid_19.Models.ImageList;
import com.alpyuktug.covid_19.Models.NewsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppDAOInerface {

    @GET("api/COVID19/CountriesJSONTR.php")
    Call<CountriesList> GetCountriesListTR();

    @GET("api/COVID19/CountriesJSONEN.php")
    Call<CountriesList> GetCountriesListEN();

    @GET("api/COVID19/NewsJSONTR.php")
    Call<NewsList> GetNewsListTR();

    @GET("api/COVID19/NewsJSONEN.php")
    Call<NewsList> GetNewsListEN();

    @GET("api/COVID19/NewsDetailsTR.php")
    Call<NewsList> GetNewsListTRDetails(@Query("NewsNumber") String NewsNumber);

    @GET("api/COVID19/MeasureJSONEN.php")
    Call<ImageList> GetImageListEN();

    @GET("api/COVID19/MeasureJSONTR.php")
    Call<ImageList> GetImageListTR();
}
