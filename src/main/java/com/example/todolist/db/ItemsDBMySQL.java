package com.example.todolist.db;

import com.example.todolist.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemsDBMySQL implements ItemsDB {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<Item> getItems() {
        return jdbcTemplate.query(
            "SELECT * FROM items",
            (rs, rowNum) -> new Item(
                rs.getInt("id"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("title"),
                rs.getBoolean("finished")
            )
        );
    }

    @Override
    @Transactional
    public Item getItemById(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM items WHERE id = ?",
            (rs, rowNum) -> new Item(
                rs.getInt("id"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("title"),
                rs.getBoolean("finished")
            ),
            id
        );
    }

    @Override
    @Transactional
    public Item createItem(String title) {
        String sql = "INSERT INTO items (title, finished) VALUES (?, ?)";
        jdbcTemplate.update(sql, title, false);

        return jdbcTemplate.queryForObject(
            "SELECT * FROM items WHERE id = LAST_INSERT_ID()",
            (rs, rowNum) -> new Item(
                rs.getInt("id"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("title"),
                rs.getBoolean("finished")
            )
        );
    }

    @Override
    @Transactional
    public boolean finishItem(int id, boolean finished) {
        String sql = "UPDATE items SET finished = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, finished, id);
        if (rowsUpdated == 0) return false;
        return true;
    }
}
