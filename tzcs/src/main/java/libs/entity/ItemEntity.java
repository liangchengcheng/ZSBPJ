package libs.entity;

/**
 * Created by xmuSistone on 2017/5/12.
 */

public class ItemEntity {

    private String country;
    private String temperature;
    private int coverImageUrl;
    private String address;
    private String description;
    private String time;
    private int mapImageUrl;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(int coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMapImageUrl() {
        return mapImageUrl;
    }

    public void setMapImageUrl(int mapImageUrl) {
        this.mapImageUrl = mapImageUrl;
    }
}
