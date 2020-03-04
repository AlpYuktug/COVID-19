package com.alpyuktug.covid_19.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alpyuktug.covid_19.Adapters.RecylerViewAdapterCountry;
import com.alpyuktug.covid_19.Models.CountriesList;
import com.alpyuktug.covid_19.Models.Covid19Country;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCountryList extends Fragment {

    public FragmentCountryList() {
    }

    private AppDAOInerface countriesDIF;
    private List<Covid19Country> CountryLists;

    public RecyclerView RecylerViewCountryList;
    public RecylerViewAdapterCountry CountryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countrylist, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RecylerViewCountryList = (RecyclerView) view.findViewById(R.id.RecylerViewCountryList);
        RecylerViewCountryList.setHasFixedSize(true);
        RecylerViewCountryList.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        countriesDIF = ApiUtils.getCountriesDAOInerface();
        AllCaountries();

        return view;
    }

    private void AllCaountries() {
        if(getString(R.string.language).contains("TR"))
        {
            countriesDIF.GetCountriesListTR().enqueue(new Callback<CountriesList>() {
                @Override
                public void onResponse(Call<CountriesList> call, Response<CountriesList> response) {

                    CountryLists = response.body().getCovid19Countries();

                    CountryAdapter = new RecylerViewAdapterCountry(CountryLists, getActivity());
                    RecylerViewCountryList.setAdapter(CountryAdapter);
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

                    CountryLists = response.body().getCovid19Countries();

                    CountryAdapter = new RecylerViewAdapterCountry(CountryLists, getActivity());
                    RecylerViewCountryList.setAdapter(CountryAdapter);
                }
                @Override
                public void onFailure(Call<CountriesList> call, Throwable t) {
                }
            });
        }
    }

}