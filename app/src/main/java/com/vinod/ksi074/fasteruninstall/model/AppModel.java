package com.vinod.ksi074.fasteruninstall.model;

import android.graphics.drawable.Drawable;

public class AppModel {

    boolean isChecked;
    private String appName;
    private String version;
    private String size;
    private Drawable image;
    private String packageName;

    public AppModel(String appName, String version, String size, Drawable image, boolean isChecked, String packageName) {
        this.appName = appName;
        this.version = version;
        this.size = size;
        this.image = image;
        this.isChecked = isChecked;
        this.packageName = packageName;

    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public AppModel() {

    }

    public Drawable getImage() {

        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
