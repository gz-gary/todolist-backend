package com.example.todolist.db;

import com.example.todolist.model.Item;

import java.util.List;

public interface ItemsDB {
    public List<Item> getItems();
    public Item getItemById(int id);
    public Item createItem(String title);
    public boolean finishItem(int id, boolean finished);
}
