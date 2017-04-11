package view.lcc.wyzsb.bean.model;

import java.io.Serializable;

public class OperationEntity implements Serializable {

    private String title;
    private String subtitle;
    private int image_url;

    public OperationEntity() {
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public OperationEntity(String title, String subtitle, int image_url) {
        this.title = title;
        this.subtitle = subtitle;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }
}
