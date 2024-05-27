package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    public List<ToDoList> findAllByUsername(String username);

    public List<ToDoList> findAllByUsernameAndColorId(String username, Integer colorId);

    @Modifying
    @Query("update to_do_lists td join users u on td.user_id = users.id " +
            "set td.title = ?1 where td.id = ?2 and td.username = ?3")
    public int setTitleByIdAndUsername(String newTitle, Integer id, String username); //returns a number of records being updated

    @Modifying
    @Query("delete from to_do_lists td join " +
            "users u on td.user_id = u.id " +
            "where td.id = ?1 and td.username = ?2")
    public int deleteByIdAndUsername(Integer id, String username); //returns a number of deleted records
}
