/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.interfaces;

/**
 *
 * @author tvan
 */
public class CustomListener {
    private static CustomListener customListenerInst = null;
    private ChatListener chatListener;
    private SearchListener searchListener;
    private MenuListener menuListener;
    private CreateGroupListener createGroupListener;
    
    private CustomListener() {
        
    }
    
    public static CustomListener getInstance() {
        if (customListenerInst == null) {
            customListenerInst = new CustomListener();
        }
        return customListenerInst;
    }
    
    public void addChatListener(ChatListener chatListener) {
        this.chatListener = chatListener;
    }

    public ChatListener getChatListener() {
        return chatListener;
    }
    
    public void addSearchListener(SearchListener searchListener) {
        this.searchListener = searchListener;
    }

    public SearchListener getSearchListener() {
        return searchListener;
    }
    
    public void addMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

    public MenuListener getMenuListener() {
        return menuListener;
    }
    
    public void addCreateGroupListener(CreateGroupListener createGroupListener) {
        this.createGroupListener = createGroupListener;
    }

    public CreateGroupListener getCreateGroupListener() {
        return createGroupListener;
    }
}
