
package com.alpyuktug.covid_19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covid19Country {

    @SerializedName("CountryNumber")
    @Expose
    private String countryNumber;

    @SerializedName("CountryCode")
    @Expose
    private String countryCode;

    @SerializedName("CountryLatitude")
    @Expose
    private String countryLatitude;

    @SerializedName("CountryLongitude")
    @Expose
    private String countryLongitude;

    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("CountryVirusCount")
    @Expose
    private String countryVirusCount;
    @SerializedName("CountryVirusDeadCount")
    @Expose
    private String countryVirusDeadCount;
    @SerializedName("CountryVirusRecoveredCount")
    @Expose
    private String countryVirusRecoveredCount;

    public String getCountryNumber() {
        return countryNumber;
    }

    public void setCountryNumber(String countryNumber) {
        this.countryNumber = countryNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryLatitude() {
        return countryLatitude;
    }

    public void setCountryLatitude(String countryLongitude) {
        this.countryLatitude = countryLatitude;
    }

    public String getCountryLongitude() {
        return countryLongitude;
    }

    public void setCountryLongitude(String countryLongitude) {
        this.countryLongitude = countryLongitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryVirusCount() {
        return countryVirusCount;
    }

    public void setCountryVirusCount(String countryVirusCount) {
        this.countryVirusCount = countryVirusCount;
    }

    public String getCountryVirusDeadCount() {
        return countryVirusDeadCount;
    }

    public void setCountryVirusDeadCount(String countryVirusDeadCount) {
        this.countryVirusDeadCount = countryVirusDeadCount;
    }

    public String getCountryVirusRecoveredCount() {
        return countryVirusRecoveredCount;
    }

    public void setCountryVirusRecoveredCount(String countryVirusRecoveredCount) {
        this.countryVirusRecoveredCount = countryVirusRecoveredCount;
    }

}
