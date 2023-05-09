package com.example.projectmobile;

import android.graphics.Bitmap;

public class ModelClass {
    String product_id;
    String price;
    String category_id;
    String ImageName;
    String Description;
    Bitmap Image;

    public ModelClass(String Product_id ,String imageName,String description, Bitmap image,String p,String c_i) {
        product_id=Product_id;
        price=p;
        category_id=c_i;
        ImageName = imageName;
        Image = image;
        Description=description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
