package com.alpyuktug.covid_19.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.alpyuktug.covid_19.Models.CountriesList;
import com.alpyuktug.covid_19.Models.Covid19Country;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.CountriesDAOInerface;


public class FragmentDashboard extends Fragment {

    public FragmentDashboard() {
    }

    private CountriesDAOInerface countriesDIF;
    private List<Covid19Country> Covid19CountryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        countriesDIF = ApiUtils.getCountriesDAOInerface();
        AllCaountries();

        return view;
    }

    private void AllCaountries()
    {
        if(getString(R.string.language).contains("TR"))
        {
            countriesDIF.GetCountriesListTR().enqueue(new Callback<CountriesList>() {

                @Override
                public void onResponse(Call<CountriesList> call, Response<CountriesList> response) {


                    Covid19CountryList = response.body().getCovid19Countries();

                    for (Covid19Country c: Covid19CountryList)
                    {
                        Log.e("Country Number",c.getCountryCode());

                    }
                }
                @Override
                public void onFailure(Call<CountriesList> call, Throwable t) {
                }
            });
        }
        else
        {
            countriesDIF.GetCountriesListEN().enqueue(new Callback<CountriesList>() {

                @Override
                public void onResponse(Call<CountriesList> call, Response<CountriesList> response) {


                    Covid19CountryList = response.body().getCovid19Countries();

                    for (Covid19Country c: Covid19CountryList)
                    {
                        Log.e("Country Number",c.getCountryCode());

                    }
                }
                @Override
                public void onFailure(Call<CountriesList> call, Throwable t) {
                }
            });
        }
    }

}
