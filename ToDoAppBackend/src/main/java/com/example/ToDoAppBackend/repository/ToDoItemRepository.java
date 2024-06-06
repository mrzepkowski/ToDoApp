package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    public List<ToDoItem> findAllByCompletedFalseAndUsernameOrderByToDoListId(String username);

    public List<ToDoItem> findAllByCompletedTrueAndUsernameOrderByToDoListId(String username);

    public List<ToDoItem> findAllByCompletedFalseAndUsernameAndToDoListId(String username, Long toDoListId);

    public List<ToDoItem> findAllByCompletedFalseAndStarredTrueAndUsernameOrderByToDoListId(String username);

    public Optional<ToDoItem> findByUsernameAndId(String username, Long id);


}
