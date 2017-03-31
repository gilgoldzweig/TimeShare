package cc.time_share.android.models;


import java.util.List;
import java.util.Set;

/**
 * Created by gilgoldzweig on 30/03/2017.
 */

public class User {
    //region Data Fields
    private String key;
    private String name;
    private String phoneNumber;
    private Double longitude;
    private Double latitude;
    private Set<String> skills;
    private List<String> skillsArray;
    private Set<String> requestKeys;
    //endregion Data Fields
    //region Constructor
    public User() {}

    public User(String name, Double longitude, Double latitude, Set<String> skills,
                Set<String> requestKeys) {
        this.name = name;
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

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public Set<String> getRequestKeys() {
        return requestKeys;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getSkillsArray() {
        return skillsArray;
    }
    //endregion getters
    //region setters

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public void setRequestKeys(Set<String> requestKeys) {
        this.requestKeys = requestKeys;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSkillsArray(List<String> skillsArray) {
        this.skillsArray = skillsArray;
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
