package cc.time_share.android.models;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Set;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class Request {
    //region Data Fields
    private String name;
    private String profileImageUrl;
    private String title;
    private String description;
    private Double longitude;
    private Double latitude;
    private List<String> needs;
    private String key;
    //endregion Data Fields
    //region Constructor
    public Request() {}

    public Request(String name, String title, String description, Double longitude, Double latitude,
                   List<String> needs) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.needs = needs;
    }
    //endregion Constructor
    //region getters
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public List<String> getNeeds() {
        return needs;
    }

    public String getKey() {
        return key;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    //endregion getters
    //region setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setNeeds(List<String> needs) {
        this.needs = needs;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    //endregion setters

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(name).append("\n");
        stringBuilder.append("Title: ").append(title).append("\n");
        stringBuilder.append("Description: ").append(description).append("\n");
        stringBuilder.append("Longitude: ").append(String.valueOf(longitude)).append("\n");
        stringBuilder.append("Latitude: ").append(String.valueOf(latitude)).append("\n");
        stringBuilder.append("Needs: ").append(needs).append("\n");
        stringBuilder.append("Key: ").append(key);
        return stringBuilder.toString();
    }

}
