
package com.alpyuktug.covid_19.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Covid19Country {

    @SerializedName("CountryNumber")
    @Expose
    private int countryNumber;

    @SerializedName("CountryCode")
    @Expose
    private String countryCode;

    @SerializedName("CountryLatitude")
    @Expose
    private Double countryLatitude;

    @SerializedName("CountryLongitude")
    @Expose
    private Double countryLongitude;

    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("CountryVirusCount")
    @Expose
    private int countryVirusCount;
    @SerializedName("CountryVirusDeadCount")
    @Expose
    private int countryVirusDeadCount;
    @SerializedName("CountryVirusRecoveredCount")
    @Expose
    private int countryVirusRecoveredCount;

    public int getCountryNumber() {
        return countryNumber;
    }

    public void setCountryNumber(int countryNumber) {
        this.countryNumber = countryNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getCountryLatitude() {
        return countryLatitude;
    }

    public void setCountryLatitude(Double countryLongitude) {
        this.countryLatitude = countryLatitude;
    }

    public Double getCountryLongitude() {
        return countryLongitude;
    }

    public void setCountryLongitude(Double countryLongitude) {
        this.countryLongitude = countryLongitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryVirusCount() {
        return countryVirusCount;
    }

    public void setCountryVirusCount(int countryVirusCount) {
        this.countryVirusCount = countryVirusCount;
    }

    public int getCountryVirusDeadCount() {
        return countryVirusDeadCount;
    }

    public void setCountryVirusDeadCount(int countryVirusDeadCount) {
        this.countryVirusDeadCount = countryVirusDeadCount;
    }

    public int getCountryVirusRecoveredCount() {
        return countryVirusRecoveredCount;
    }

    public void setCountryVirusRecoveredCount(int countryVirusRecoveredCount) {
        this.countryVirusRecoveredCount = countryVirusRecoveredCount;
    }

}
