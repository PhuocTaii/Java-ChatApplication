package com.btv.Admin.gui.components;

import com.btv.Admin.gui.interfaces.EventMenuSelected;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class MenuList<E extends Object> extends JList<Object> {

    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int hoveredIndex = -1;
    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }

    public MenuList() {
        model = new DefaultListModel();
        setModel(model);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    Object o = model.getElementAt(index);
                    MenuModel menu = (MenuModel) o;
                    selectedIndex = index;
                    if (event != null) {
                        event.selected(index);
                    }
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                hoveredIndex = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                int index = locationToIndex(me.getPoint());
                if (index != hoveredIndex) {
                    Object o = model.getElementAt(index);
                    MenuModel menu = (MenuModel) o;
                    hoveredIndex = index;

                    repaint();

                }
            }
        });
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
                item.setIsSelected(selectedIndex == index);
                item.setIsHovered(hoveredIndex == index);
                return item;
            }
        };
    }

    public void addItem(MenuModel data) {
        model.addElement(data);
    }

}
