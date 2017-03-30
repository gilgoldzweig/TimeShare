package cc.time_share.android.models;

import java.util.Set;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class Request {
    //region Data Fields
    private String title;
    private String description;
    private Double longitude;
    private Double latitude;
    private Set<String> needs;
    private String key;
    //endregion Data Fields
    //region Constructor
    public Request() {}

    public Request(String title, String description, Double longitude, Double latitude,
                   Set<String> needs, String key) {
        this.title = title;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.needs = needs;
        this.key = key;
    }
    //endregion Constructor
    //region getters
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

    public Set<String> getNeeds() {
        return needs;
    }

    public String getKey() {
        return key;
    }
    //endregion getters
    //region setters
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

    public void setNeeds(Set<String> needs) {
        this.needs = needs;
    }

    public void setKey(String key) {
        this.key = key;
    }
    //endregion setters


    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append("Title: ").append(title).append("\n");
        toStringBuilder.append("Description: ").append(description).append("\n");
        toStringBuilder.append("Longitude: ").append(String.valueOf(longitude)).append("\n");
        toStringBuilder.append("Latitude: ").append(String.valueOf(latitude)).append("\n");
        toStringBuilder.append("Needs: ").append(needs).append("\n");
        toStringBuilder.append("Key: ").append(key);
        return toStringBuilder.toString();
    }
}