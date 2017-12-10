package io.plan8.backoffice.model.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.linkedin.android.spyglass.mentions.Mentionable;
import com.linkedin.android.spyglass.tokenization.QueryToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.util.MentionsLoader;

/**
 * Created by SSozi on 2017. 12. 7..
 */

public class Worker implements BaseModel, Mentionable {
    @SerializedName("id")
    int id;
    @SerializedName("created")
    String created;
    @SerializedName("updated")
    String updated;
    @SerializedName("configuration")
    Configuration configuration;
    @SerializedName("name")
    String name;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("username")
    String username;
    @SerializedName("mobileNumber")
    String mobileNumber;
    @SerializedName("email")
    String email;
    @SerializedName("owner")
    boolean owner;
    @SerializedName("admin")
    boolean admin;
    @SerializedName("deactivated")
    boolean deactivated;


    public Worker() {}

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isOwner() {
        return owner;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    @NonNull
    @Override
    public String getTextForDisplayMode(Mentionable.MentionDisplayMode mode) {
        switch (mode) {
            case FULL:
                return username;
            case PARTIAL:
                String[] words = username.split(" ");
                return (words.length > 1) ? words[0] : "";
            case NONE:
            default:
                return "";
        }
    }

    @Override
    public Mentionable.MentionDeleteStyle getDeleteStyle() {
        // Note: Cities do not support partial deletion
        // i.e. "San Francisco" -> DEL -> ""
        return Mentionable.MentionDeleteStyle.PARTIAL_NAME_DELETE;
    }

    @Override
    public int getSuggestibleId() {
        return username.hashCode();
    }

    @Override
    public String getSuggestiblePrimaryText() {
        return username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeString(username);
    }

    public Worker(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        username = in.readString();
    }

    public static final Parcelable.Creator<Worker> CREATOR
            = new Parcelable.Creator<Worker>() {
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };

    // --------------------------------------------------
    // CityLoader Class (loads cities from JSON file)
    // --------------------------------------------------

    public static class MemberLoader extends MentionsLoader<Worker> {
        private static final String TAG = Worker.MemberLoader.class.getSimpleName();
        private List<Worker> teamList;

        public MemberLoader(List<Worker> teamList) {
            super();
            this.teamList = teamList;
        }

        @Override
        public Worker[] loadData(JSONArray arr) {
            return teamList.toArray(new Worker[0]);
        }

        // Modified to return suggestions based on both first and last name
        @Override
        public List<Worker> getSuggestions(QueryToken queryToken) {
            String[] namePrefixes = queryToken.getKeywords().toLowerCase().split(" ");
            List<Worker> suggestions = new ArrayList<>();
            if (teamList != null) {
                for (Worker suggestion : teamList) {
                    String suggestionUserName = suggestion.getUsername().toLowerCase();
                    String suggestionName = suggestion.getName().toLowerCase();

                    if (suggestionName.contains(namePrefixes[0]) || suggestionUserName.contains(namePrefixes[0])) {
                        suggestions.add(suggestion);
                    }
                }
            }
            return suggestions;
        }
    }
}
