package cc.time_share.android.models;

/**
 * Created by gilgoldzweig on 01/04/2017.
 */

public class Transaction {
    private String coverImage;
    private Request request;

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
