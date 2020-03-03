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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

    List<String> AllCountryVirusCount = new ArrayList<String>();
    List<String> AllCountryVirusDeadCount = new ArrayList<String>();
    List<String> AllCountryVirusRecovered = new ArrayList<String>();

    GoogleMap mapView;
    SupportMapFragment mapFragment;

    String VirusCount,VirusDeadCount,VirusRecovered;

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

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {

                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomGesturesEnabled(false);

                mMap.clear();

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4629101,-122.2449094))
                        .title("Iron Man")
                        .snippet("His Talent : Plenty of money"));

            }
        });

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


                    Covid19CountryList = response.body().getCovid19Countries();

                    for (Covid19Country c: Covid19CountryList)
                    {
                        Double CountryLatitude = c.getCountryLatitude();
                        Double CountryLongitude = c.getCountryLongitude();

                        String CountryVirusCount =  c.getCountryVirusCount();
                        String CountryVirusDeadCount =  c.getCountryVirusDeadCount();
                        String CountryVirusRecovered =  c.getCountryVirusRecoveredCount();

                        AllCountryVirusCount.add(CountryVirusCount);
                        AllCountryVirusDeadCount.add(CountryVirusDeadCount);
                        AllCountryVirusRecovered.add(CountryVirusRecovered);

                        LatLng CountryCoord = new LatLng(CountryLatitude, CountryLongitude);
                        AllCountryCoord.add(CountryCoord);
                    }

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

                            /*
                            int height = 80;
                            int width = 80;
                            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bordoarac);
                            Bitmap b = bitmapdraw.getBitmap();
                            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
*/

                            for (int i=0; i < AllCountryCoord.size(); i++)
                            {
                                mMap.addMarker(new MarkerOptions()
                                        .position(AllCountryCoord.get(i))
                                        .title("COVID-19")
                                        .snippet(VirusCount +" : "+AllCountryVirusCount.get(i)+ "\n" + VirusDeadCount +" : "+AllCountryVirusDeadCount.get(i) + "\n"  + VirusRecovered +" : "+AllCountryVirusRecovered.get(i)   ));
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
