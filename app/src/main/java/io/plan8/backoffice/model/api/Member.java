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

public class Member implements BaseModel, Mentionable {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("avatar") private String avatar;
    @SerializedName("username") private String username;
    @SerializedName("mobileNumber") private String mobileNumber;
    @SerializedName("email") private String email;
    @SerializedName("owner") private boolean owner;
    @SerializedName("admin") private boolean admin;
    @SerializedName("daysOff") private List<DayOff> daysOff;
    @SerializedName("configuration") private Configuration configuration;
    @SerializedName("deactivated") private boolean deactivated;

    public Member() {}

    public int getId() {
        return id;
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

    public List<DayOff> getDaysOff() {
        return daysOff;
    }

    public Configuration getConfiguration() {
        return configuration;
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

    public Member(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        username = in.readString();
    }

    public static final Parcelable.Creator<Member> CREATOR
            = new Parcelable.Creator<Member>() {
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    // --------------------------------------------------
    // CityLoader Class (loads cities from JSON file)
    // --------------------------------------------------

    public static class MemberLoader extends MentionsLoader<Member> {
        private static final String TAG = Member.MemberLoader.class.getSimpleName();
        private List<Member> teamList;

        public MemberLoader(List<Member> teamList) {
            super();
            this.teamList = teamList;
        }

        @Override
        public Member[] loadData(JSONArray arr) {
            return teamList.toArray(new Member[0]);
        }

        // Modified to return suggestions based on both first and last name
        @Override
        public List<Member> getSuggestions(QueryToken queryToken) {
            String[] namePrefixes = queryToken.getKeywords().toLowerCase().split(" ");
            List<Member> suggestions = new ArrayList<>();
            if (teamList != null) {
                for (Member suggestion : teamList) {
                    String name = suggestion.getUsername().toLowerCase();
                    if (name.startsWith(namePrefixes[0])) {
                        suggestions.add(suggestion);
                    }
                }
            }
            return suggestions;
        }
    }
}
