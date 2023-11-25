package com.btv.component;

import com.btv.model.MenuModel;
import java.awt.Component;
import java.awt.event.MouseAdapter;
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
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent me) {
//                if (SwingUtilities.isLeftMouseButton(me)) {
//                    int index = locationToIndex(me.getPoint());
//                    Object o = model.getElementAt(index);
//                    if (o instanceof Model_Menu) {
//                        Model_Menu menu = (Model_Menu) o;
//                        if (menu.getType() == Model_Menu.MenuType.MENU) {
//                            selectedIndex = index;
//                        }
//                    } else {
//                        selectedIndex = index;
//                    }
//                    repaint();
//                }
//            }
//        });
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
                    data = new MenuModel("", o + "", MenuModel.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data);
//                item.setSelected(selectedIndex == index);
                return item;
            }
        };
    }
    
    public void addItem(MenuModel data) {
        model.addElement(data);
    }
    
}
