package com.btv.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MenuModel {

    public MenuModel(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
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

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Icon toIcon() {
        System.out.println(getClass().getResource("/com/btv/image/" + icon + ".png"));
        return new ImageIcon(getClass().getResource("/com/btv/image/" + icon + ".png"));
    }

    String icon;
    String name;
    MenuType type;
}
