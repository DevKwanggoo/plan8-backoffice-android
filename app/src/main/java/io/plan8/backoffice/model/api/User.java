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
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class User implements BaseModel, Mentionable {
    @SerializedName("mobileNumber")
    String mobileNumber;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("id")
    String userId;
    @SerializedName("updated")
    String updated;
    @SerializedName("created")
    String created;
    @SerializedName("username")
    String userName;
    @SerializedName("name")
    String name;
    @SerializedName("hasPassword")
    boolean hasPassword;

    public User() {
    }

    public User(String userName, String avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserId() {
        return userId;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCreated() {
        return created;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    @NonNull
    @Override
    public String getTextForDisplayMode(MentionDisplayMode mode) {
        switch (mode) {
            case FULL:
                return userName;
            case PARTIAL:
                String[] words = userName.split(" ");
                return (words.length > 1) ? words[0] : "";
            case NONE:
            default:
                return "";
        }
    }

    @Override
    public MentionDeleteStyle getDeleteStyle() {
        // Note: Cities do not support partial deletion
        // i.e. "San Francisco" -> DEL -> ""
        return MentionDeleteStyle.PARTIAL_NAME_DELETE;
    }

    @Override
    public int getSuggestibleId() {
        return userName.hashCode();
    }

    @Override
    public String getSuggestiblePrimaryText() {
        return userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(avatar);
    }

    public User(Parcel in) {
        userName = in.readString();
        avatar = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // --------------------------------------------------
    // CityLoader Class (loads cities from JSON file)
    // --------------------------------------------------

    public static class UserLoader extends MentionsLoader<User> {
        private static final String TAG = UserLoader.class.getSimpleName();
        private List<User> teamList;

        public UserLoader(List<User> teamList) {
            super();
            this.teamList = teamList;
        }

        @Override
        public User[] loadData(JSONArray arr) {
            return teamList.toArray(new User[0]);
        }

        // Modified to return suggestions based on both first and last name
        @Override
        public List<User> getSuggestions(QueryToken queryToken) {
            String[] namePrefixes = queryToken.getKeywords().toLowerCase().split(" ");
            List<User> suggestions = new ArrayList<>();
            if (teamList != null) {
                for (User suggestion : teamList) {
                    String name = suggestion.getUserName().toLowerCase();
                    if (name.startsWith(namePrefixes[0])) {
                        suggestions.add(suggestion);
                    }
                }
            }
            return suggestions;
        }
    }
}