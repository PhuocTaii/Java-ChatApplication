package com.btv.component;

import com.btv.model.MenuModel;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MenuList<E extends Object> extends JList<Object> {

    private final DefaultListModel model;
    private int selectedIndex = -1;

    public MenuList() {
        model = new DefaultListModel();
        setModel(model);
    }

    @Override
    public ListCellRenderer<? super Object> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                MenuModel data;
                if (o instanceof MenuModel) {
                    data = (MenuModel) o;
                } else {
                    data = new MenuModel("", o + "");
                }
                MenuItem item = new MenuItem(data);
                return item;
            }
        };
    }

    public void addItem(MenuModel data) {
        model.addElement(data);
    }

}
