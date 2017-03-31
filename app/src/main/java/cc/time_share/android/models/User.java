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
    private Float longitude;
    private Float latitude;
    private Set<String> skills;
    private List<String> skillsArray;
    private Set<String> requestKeys;
    //endregion Data Fields
    //region Constructor
    public User() {}

    public User(String name, String phoneNumber, Float longitude, Float latitude,
                Set<String> skills, Set<String> requestKeys) {
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

    public Set<String> getSkills() {
        return skills;
    }

    public Set<String> getRequestKeys() {
        return requestKeys;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public void setRequestKeys(Set<String> requestKeys) {
        this.requestKeys = requestKeys;
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
