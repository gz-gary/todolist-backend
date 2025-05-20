package com.example.todolist.db;

import com.example.todolist.model.Item;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@Primary
public class MockItemsDB implements ItemsDB {
    private int lastId = 0;
    private List<Item> items = new LinkedList<>();

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public Item getItemById(int id) {
        for (Item item : items) if (item.getId() == id) return item;
        return null;
    }

    @Override
    public Item createItem(String title) {
        Item item = new Item(lastId++, LocalDateTime.now(), title, false);
        items.add(item);
        return item;
    }

    @Override
    public boolean finishItem(int id, boolean finished) {
        Item item = getItemById(id);
        if (item == null) return false;
        item.setFinished(finished);
        return true;
    }
}
