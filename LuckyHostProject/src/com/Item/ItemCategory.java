package com.Item;

import java.util.Vector;
/**
 * 这个类用于构造一个容器来存储需要使用的Item
 * @author WuSigan
 * @version 0.0.1
 */


public class ItemCategory {
    /**
     * 用于存储Item的容器
     */
    protected Vector<Item> itemCategory = new Vector<Item>();

    /**
     * 含参构造函数
     * @param itemCategory
     */
    public ItemCategory(Vector<Item> itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * 无参构造函数
     */
    public ItemCategory() {
    }

    /**
     * 返回存储了Item的容器
     * @return Vector<Item>
     */
    public Vector<Item> getItemCategory() {
        return itemCategory;
    }

    /**
     * 设置或更新存储Item的容器
     * @param itemCategory
     */
    public void setItemCategory(Vector<Item> itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * 添加一个Item进入容器
     * @param item
     */
    public void addItem(Item item){
        itemCategory.add(item);
    }

    /**
     * 按index（即序号）删除容器中的Item
     * @param index
     */
    public void removeItem(int index){
        itemCategory.remove(index);
    }

}
