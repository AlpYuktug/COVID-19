
package com.alpyuktug.covid_19;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountriesList {

    @SerializedName("Covid19Countries")
    @Expose
    private List<Covid19Country> covid19Countries = null;

    public List<Covid19Country> getCovid19Countries() {
        return covid19Countries;
    }

    public void setCovid19Countries(List<Covid19Country> covid19Countries) {
        this.covid19Countries = covid19Countries;
    }

}
