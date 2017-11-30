package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Front implements BaseModel {
    @SerializedName("brandName") String brandName;
    @SerializedName("brandLogo") String brandLogo;
    @SerializedName("color") Color color;
    @SerializedName("description") Description description;
    @SerializedName("enableAdditionalRequests") boolean enableAdditionalRequests;
    @SerializedName("terms") String terms;
    @SerializedName("durationOfTemporaryReservation") int durationOfTemporaryReservation;
    @SerializedName("cancelableHours") int cancelableHours;
    @SerializedName("addressRestrictionRules") String addressRestrictionRules;

    public String getBrandName() {
        return brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public Color getColor() {
        return color;
    }

    public Description getDescription() {
        return description;
    }

    public boolean isEnableAdditionalRequests() {
        return enableAdditionalRequests;
    }

    public String getTerms() {
        return terms;
    }

    public int getDurationOfTemporaryReservation() {
        return durationOfTemporaryReservation;
    }

    public int getCancelableHours() {
        return cancelableHours;
    }

    public String getAddressRestrictionRules() {
        return addressRestrictionRules;
    }

    private class Color implements BaseModel {
        @SerializedName("link") String link;
        @SerializedName("theme") String theme;

        public String getLink() {
            return link;
        }

        public String getTheme() {
            return theme;
        }
    }

    private class Description implements BaseModel {
        @SerializedName("main") String main;
        @SerializedName("product") String product;
        @SerializedName("address") String address;
        @SerializedName("additionalRequests") String additionalRequests;

        public String getMain() {
            return main;
        }

        public String getProduct() {
            return product;
        }

        public String getAddress() {
            return address;
        }

        public String getAdditionalRequests() {
            return additionalRequests;
        }
    }
}
