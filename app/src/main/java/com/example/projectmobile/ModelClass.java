package com.example.projectmobile;

import android.graphics.Bitmap;

public class ModelClass {
    String ImageName;
    String Description;
    Bitmap Image;

    public ModelClass(String imageName,String description, Bitmap image) {
        ImageName = imageName;
        Image = image;
        Description=description;
    }

    public String getImageName() {
        return ImageName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }
}
