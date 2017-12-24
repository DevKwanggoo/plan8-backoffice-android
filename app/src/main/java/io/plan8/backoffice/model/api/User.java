package io.plan8.backoffice.model.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.linkedin.android.spyglass.mentions.Mentionable;
import com.linkedin.android.spyglass.tokenization.QueryToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.util.MentionsLoader;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class User implements BaseModel, Mentionable {
    @SerializedName("id")
    @Expose()
    int id;
    @SerializedName("added")
    @Expose()
    String added;
    @SerializedName("edited")
    @Expose()
    String edited;
    @SerializedName("publicId")
    @Expose()
    String publicId;
    @SerializedName("username")
    @Expose()
    String username;
    @SerializedName("phoneNumber")
    @Expose()
    String phoneNumber;
    @SerializedName("email")
    @Expose()
    String email;
    @SerializedName("password")
    @Expose()
    String password;
    @SerializedName("name")
    @Expose()
    String name;
    @SerializedName("avatar")
    @Expose()
    String avatar;
    @SerializedName("verified")
    @Expose()
    boolean verified;
    @SerializedName("hasPassword")
    @Expose()
    boolean hasPassword;
    @SerializedName("hasName")
    @Expose()
    boolean hasName;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        if (null == avatar || avatar.equals("")) {
            return Constants.DEFAULT_USER_AVATAR_URL(id);
        }
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public boolean isHasName() {
        return hasName;
    }

    public void setHasName(boolean hasName) {
        this.hasName = hasName;
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

    public User(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        username = in.readString();
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
        private static final String TAG = User.UserLoader.class.getSimpleName();
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
            for (int i = 0; i < namePrefixes.length; i++) {
                Log.e("mentionTest", "namePrifiex[" + i + "] = " + namePrefixes[i]);
            }
            List<User> suggestions = new ArrayList<>();
            if (teamList != null) {
                for (User suggestion : teamList) {
                    if (null != suggestion.getUsername() && null != suggestion.getName()) {
                        String suggestionUserName = suggestion.getUsername().toLowerCase();
                        String suggestionName = suggestion.getName().toLowerCase();

                        if (suggestionName.contains(namePrefixes[0]) || suggestionUserName.contains(namePrefixes[0])) {
                            suggestions.add(suggestion);
                        }
                    }
                }
            }
            return suggestions;
        }
    }
}