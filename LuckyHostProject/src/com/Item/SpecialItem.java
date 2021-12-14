package com.Item;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 对特殊Item的扩展
 * @see
 */
public class SpecialItem extends Item{

    public SpecialItem() {
    }

    public SpecialItem(String id, String name, JButton icon, String description) {
        super(id, name, icon, description);
    }
}
