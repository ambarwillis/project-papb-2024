package com.pam.deertoapp.JadwalSection;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insert(ItemModel... items);

    @Query("DELETE FROM item_table")
    void deleteAllItems();

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    List<ItemModel> getAllItems();
}
