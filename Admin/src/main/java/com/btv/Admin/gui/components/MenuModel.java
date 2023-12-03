package com.btv.Admin.gui.components;

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
        return new ImageIcon(getClass().getResource("/com/btv/images/" + icon + ".png"));
    }

    String icon;
    String name;
}
