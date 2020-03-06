package com.alpyuktug.covid_19.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.alpyuktug.covid_19.Models.CountriesList;
import com.alpyuktug.covid_19.Models.Covid19Country;
import com.alpyuktug.covid_19.R;
import com.alpyuktug.covid_19.Services.ApiUtils;
import com.alpyuktug.covid_19.Services.AppDAOInerface;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class FragmentDashboard extends Fragment{

    public FragmentDashboard() {

    }

    private AppDAOInerface countriesDIF;
    private List<Covid19Country> Covid19CountryList;

    List<LatLng> AllCountryCoord = new ArrayList<LatLng>();

    List<Integer> AllCountryVirusCount = new ArrayList<Integer>();
    List<Integer> AllCountryVirusDeadCount = new ArrayList<Integer>();
    List<Integer> AllCountryVirusRecovered = new ArrayList<Integer>();
    List<String> CountriesName = new ArrayList<String>();

    SupportMapFragment mapFragment;

    String VirusCount,VirusDeadCount,VirusRecovered;
    String WorldVirusCount,WorldVirusDeadCount,WorldVirusRecovered;

    int TotalVirus=0, TotalDead=0, TotalRecover=0;

    TextView textViewAll,textViewDeath,textViewRecover;
    ImageView imageViewGetList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        VirusCount = getContext().getResources().getString(R.string.CountryVirusCount);
        VirusDeadCount = getContext().getResources().getString(R.string.CountryVirusDeadCount);
        VirusRecovered = getContext().getResources().getString(R.string.CountryVirusRecovered);

        WorldVirusCount = getContext().getResources().getString(R.string.WorldVirusCount);
        WorldVirusDeadCount = getContext().getResources().getString(R.string.WorldVirusDeadCount);
        WorldVirusRecovered = getContext().getResources().getString(R.string.WorldVirusRecovered);

        textViewAll = view.findViewById(R.id.textViewAll);
        textViewDeath = view.findViewById(R.id.textViewDeath);
        textViewRecover = view.findViewById(R.id.textViewRecover);
        imageViewGetList = view.findViewById(R.id.imageViewGetList);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomGesturesEnabled(false);
                mMap.clear();
            }
        });

        countriesDIF = ApiUtils.getCountriesDAOInerface();
        AllCaountries();

        imageViewGetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( v == imageViewGetList)
                {
                    Fragment fragment = new FragmentCountryList();
                    getFragmentManager().beginTransaction().replace(R.id.FragmentContent, fragment).commit();
                }
            }
        });

        return view;
    }

    private void AllCaountries() {
        if(getString(R.string.language).contains("TR"))
        {
            countriesDIF.GetCountriesListTR().enqueue(new Callback<CountriesList>() {
                @Override
                public void onResponse(Call<CountriesList> call, Response<CountriesList> response) {

                    Covid19CountryList = response.body().getCovid19Countries();

                    for (Covid19Country c: Covid19CountryList)
                    {
                        Double CountryLatitude = c.getCountryLatitude();
                        Double CountryLongitude = c.getCountryLongitude();

                        String CountryName = c.getCountryName();
                        int CountryVirusCount =  c.getCountryVirusCount();
                        int CountryVirusDeadCount =  c.getCountryVirusDeadCount();
                        int CountryVirusRecovered =  c.getCountryVirusRecoveredCount();

                        AllCountryVirusCount.add(CountryVirusCount);
                        AllCountryVirusDeadCount.add(CountryVirusDeadCount);
                        AllCountryVirusRecovered.add(CountryVirusRecovered);
                        CountriesName.add(CountryName);

                        LatLng CountryCoord = new LatLng(CountryLatitude, CountryLongitude);
                        AllCountryCoord.add(CountryCoord);

                        TotalVirus = TotalVirus + CountryVirusCount;
                        TotalDead = TotalDead + CountryVirusDeadCount;
                        TotalRecover = TotalRecover + CountryVirusRecovered;
                    }

                    textViewAll.setText(WorldVirusCount + " : " + String.valueOf(TotalVirus));
                    textViewDeath.setText(WorldVirusDeadCount + " : " + String.valueOf(TotalDead));
                    textViewRecover.setText(WorldVirusRecovered + " : " + String.valueOf(TotalRecover));

                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap mMap) {

                            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                @Override
                                public View getInfoWindow(Marker marker) {
                                    return null;
                                }

                                @Override
                                public View getInfoContents(Marker marker) {

                                    Context mContext = getContext();

                                    LinearLayout Info = new LinearLayout(mContext);
                                    Info.setOrientation(LinearLayout.VERTICAL);

                                    TextView Title = new TextView(mContext);
                                    Title.setTextColor(Color.BLACK);
                                    Title.setGravity(Gravity.CENTER);
                                    Title.setTextSize(20);
                                    Title.setTypeface(null, Typeface.BOLD);
                                    Title.setText(marker.getTitle());

                                    TextView Snippet = new TextView(mContext);
                                    Snippet.setTextColor(Color.BLACK);
                                    Snippet.setTextSize(20);
                                    Snippet.setText(marker.getSnippet());

                                    Info.addView(Title);
                                    Info.addView(Snippet);

                                    return Info;
                                }
                            });

                            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            mMap.getUiSettings().setZoomGesturesEnabled(false);

                            mMap.clear();

                            int height = 58;
                            int width = 58;
                            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bacteria);
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap bacteria = Bitmap.createScaledBitmap(b, width, height, false);

                            for (int i=0; i < AllCountryCoord.size(); i++)
                            {
                                mMap.addMarker(new MarkerOptions()
                                        .position(AllCountryCoord.get(i))
                                        .title(CountriesName.get(i))
                                        .snippet(VirusCount +" : "+AllCountryVirusCount.get(i)+ "\n" + VirusDeadCount +" : "+AllCountryVirusDeadCount.get(i) + "\n"  + VirusRecovered +" : "+AllCountryVirusRecovered.get(i))
                                        .icon(BitmapDescriptorFactory.fromBitmap(bacteria)));
                            }
                        }
                    });
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
                        Double CountryLatitude = c.getCountryLatitude();
                        Double CountryLongitude = c.getCountryLongitude();

                        String CountryName = c.getCountryName();
                        int CountryVirusCount =  c.getCountryVirusCount();
                        int CountryVirusDeadCount =  c.getCountryVirusDeadCount();
                        int CountryVirusRecovered =  c.getCountryVirusRecoveredCount();

                        AllCountryVirusCount.add(CountryVirusCount);
                        AllCountryVirusDeadCount.add(CountryVirusDeadCount);
                        AllCountryVirusRecovered.add(CountryVirusRecovered);
                        CountriesName.add(CountryName);

                        LatLng CountryCoord = new LatLng(CountryLatitude, CountryLongitude);
                        AllCountryCoord.add(CountryCoord);

                        TotalVirus = TotalVirus + CountryVirusCount;
                        TotalDead = TotalDead + CountryVirusDeadCount;
                        TotalRecover = TotalRecover + CountryVirusRecovered;
                    }

                    textViewAll.setText(WorldVirusCount + ":" + String.valueOf(TotalVirus));
                    textViewDeath.setText(WorldVirusDeadCount + ":" + String.valueOf(TotalDead));
                    textViewRecover.setText(WorldVirusRecovered + ":" + String.valueOf(TotalRecover));

                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap mMap) {

                            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                @Override
                                public View getInfoWindow(Marker marker) {
                                    return null;
                                }

                                @Override
                                public View getInfoContents(Marker marker) {

                                    Context mContext = getContext();

                                    LinearLayout Info = new LinearLayout(mContext);
                                    Info.setOrientation(LinearLayout.VERTICAL);

                                    TextView Title = new TextView(mContext);
                                    Title.setTextColor(Color.BLACK);
                                    Title.setGravity(Gravity.CENTER);
                                    Title.setTextSize(20);
                                    Title.setTypeface(null, Typeface.BOLD);
                                    Title.setText(marker.getTitle());

                                    TextView Snippet = new TextView(mContext);
                                    Snippet.setTextColor(Color.BLACK);
                                    Snippet.setTextSize(20);
                                    Snippet.setText(marker.getSnippet());

                                    Info.addView(Title);
                                    Info.addView(Snippet);

                                    return Info;
                                }
                            });

                            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                            mMap.getUiSettings().setZoomGesturesEnabled(false);

                            mMap.clear();


                            int height = 58;
                            int width = 58;
                            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bacteria);
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap bacteria = Bitmap.createScaledBitmap(b, width, height, false);

                            for (int i=0; i < AllCountryCoord.size(); i++)
                            {
                                mMap.addMarker(new MarkerOptions()
                                        .position(AllCountryCoord.get(i))
                                        .title(CountriesName.get(i))
                                        .snippet(VirusCount +" : "+AllCountryVirusCount.get(i)+ "\n" + VirusDeadCount +" : "+AllCountryVirusDeadCount.get(i) + "\n"  + VirusRecovered +" : "+AllCountryVirusRecovered.get(i))
                                        .icon(BitmapDescriptorFactory.fromBitmap(bacteria)));
                            }
                        }
                    });
                }
                @Override
                public void onFailure(Call<CountriesList> call, Throwable t) {
                }
            });
        }
    }
}
