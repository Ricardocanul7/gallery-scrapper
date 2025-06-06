package com.gallery_scrapper.gallery_scrapper.shared.domain;

public class ImageDTO {
    public String title;
    public String src;
    public String alt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public String getSrc() {
        return src;
    }

    public String getAlt() {
        return alt;
    }
}
