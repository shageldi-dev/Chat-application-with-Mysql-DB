package com.example.oguzhanchat;

public class CustomItem {
    private String spinnerItemName;
    private String spinnerItemLevel;
    private int spinnerItemImage;

    public CustomItem(String spinnerItemName,String spinnerItemLevel,int spinnerItemImage) {
        this.spinnerItemName = spinnerItemName;
        this.spinnerItemLevel = spinnerItemLevel;
        this.spinnerItemImage = spinnerItemImage;
    }

    public String getSpinnerItemName() {
        return spinnerItemName;
    }

    public String getSpinnerItemLevel() {
        return spinnerItemLevel;
    }

    public int getSpinnerItemImage() {
        return spinnerItemImage;
    }


}
