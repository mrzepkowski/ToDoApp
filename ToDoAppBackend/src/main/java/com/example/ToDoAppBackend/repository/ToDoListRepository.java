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
    @Query("update users u set u.title = ?1 where u.id = ?2")
    public int setToDoListTitleById(String newTitle, Integer id); //returns number of records being updated

    void deleteById(Integer id);
}
