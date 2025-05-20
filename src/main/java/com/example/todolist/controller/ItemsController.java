package com.example.todolist.controller;

import com.example.todolist.db.ItemsDB;
import com.example.todolist.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsController {
    @Autowired
    private ItemsDB itemsDB;

    @GetMapping
    public List<Item> getItems() {
        return itemsDB.getItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = itemsDB.getItemById(id);
        if (item == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        Item newItem = itemsDB.createItem(item.getTitle());
        return newItem;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> finishItem(@PathVariable int id, @RequestBody Item item) {
        if (!itemsDB.finishItem(id, item.isFinished()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(itemsDB.getItemById(id), HttpStatus.OK);
    }
}