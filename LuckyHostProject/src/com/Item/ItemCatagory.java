package com.Item;

import java.util.Vector;
/**
 * This Class
 * @author WuSigan
 * @version 0.0.1
 */

public class ItemCatagory {
    /**
     * 用于存储Item的容器
     */
    public Vector<Item> itemCatagory = new Vector<Item>();

    /**
     * 含参构造函数
     * @param itemCatagory
     */
    public ItemCatagory(Vector<Item> itemCatagory) {
        this.itemCatagory = itemCatagory;
    }

    /**
     * 无参构造函数
     */
    public ItemCatagory() {
    }

    /**
     * 返回存储了Item的容器
     * @return Vector<Item>
     */
    public Vector<Item> getItemCatagory() {
        return itemCatagory;
    }

    /**
     * 设置或更新存储Item的容器
     * @param itemCatagory
     */
    public void setItemCatagory(Vector<Item> itemCatagory) {
        this.itemCatagory = itemCatagory;
    }

    /**
     * 添加一个Item进入容器
     * @param item
     */
    public void addItem(Item item){
        itemCatagory.add(item);
    }

    /**
     * 按index（即序号）删除容器中的Item
     * @param index
     */
    public void removeItem(int index){
        itemCatagory.remove(index);
    }

}
