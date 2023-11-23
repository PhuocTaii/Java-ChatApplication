package com.btv.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MenuModel {

    public MenuModel(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Icon toIcon() {
        System.out.println(getClass().getResource("/com/btv/image/" + icon + ".png"));
        return new ImageIcon(getClass().getResource("/com/btv/image/" + icon + ".png"));
    }

    String icon;
    String name;
}
