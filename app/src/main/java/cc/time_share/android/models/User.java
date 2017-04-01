package cc.time_share.android.models;


import java.util.List;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class User {
    //region Data Fields
    private String key;
    private String name;
    private String phoneNumber;
    private Float longitude;
    private Float latitude;
    private List<String> skills;
    private List<String> requestKeys;
    //endregion Data Fields
    //region Constructor
    public User() {}

    public User(String name, String phoneNumber, Float longitude, Float latitude,
                List<String> skills, List<String> requestKeys) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.skills = skills;
        this.requestKeys = requestKeys;
    }

    //endregion Constructor
    //region getters

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getRequestKeys() {
        return requestKeys;
    }

    //endregion getters
    //region setters

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setRequestKeys(List<String> requestKeys) {
        this.requestKeys = requestKeys;
    }

    //endregion setters

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append("Name: ").append(name).append("\n");
        toStringBuilder.append("Longitude: ").append(String.valueOf(longitude)).append("\n");
        toStringBuilder.append("Latitude: ").append(String.valueOf(latitude)).append("\n");
        toStringBuilder.append("Skills: ").append(skills).append("\n");
        toStringBuilder.append("RequestKeys: ").append(requestKeys);
        return toStringBuilder.toString();
    }
}
