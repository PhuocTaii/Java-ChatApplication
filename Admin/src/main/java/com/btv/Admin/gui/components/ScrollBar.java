<<<<<<<< HEAD:User/src/main/java/com/btv/User/gui/components/ScrollBar.java
package com.btv.User.gui.components;
========
package com.btv.Admin.gui.components;
>>>>>>>> master:Admin/src/main/java/com/btv/Admin/gui/components/ScrollBar.java

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar{
    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setBackground(new Color(242, 242, 242));
        setUnitIncrement(20);
    }
}
