package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.dto.ToDoItemDTO;
import com.example.ToDoAppBackend.dto.ToDoListDTO;
import com.example.ToDoAppBackend.entity.ToDoItem;
import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.repository.ToDoItemRepository;
import com.example.ToDoAppBackend.repository.ToDoListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ToDoItemService {
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    public List<ToDoItem> loadAll(String username) {
        return toDoItemRepository.findAllByCompletedFalseAndUsernameOrderByToDoListId(username);
    }

    public List<ToDoItem> loadAllCompleted(String username) {
        return toDoItemRepository.findAllByCompletedTrueAndUsernameOrderByToDoListId(username);
    }

    public List<ToDoItem> loadAllByToDoListId(String username, Long toDoListId) {
        return toDoItemRepository.findAllByCompletedFalseAndUsernameAndToDoListId(username, toDoListId);
    }

    public List<ToDoItem> loadAllStarred(String username) {
        return toDoItemRepository.findAllByCompletedFalseAndStarredTrueAndUsernameOrderByToDoListId(username);
    }

    public ToDoItem loadById(String username, Long id) {
        return toDoItemRepository.findByUsernameAndId(username, id)
                .orElseThrow(() -> new EntityNotFoundException("To-do item not found"));
    }

    public ToDoItem save(String username, ToDoItemDTO.SaveRequest request) {
        ToDoList toDoList = toDoListRepository.findByuserUsernameAndId(username, request.toDoListId())
                .orElseThrow(() -> new EntityNotFoundException("To-do list not found"));

        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setContent(request.content());
        toDoItem.setToDoList(toDoList);
        toDoItem.setUsername(username);

        if (request.deadline() != null)
            toDoItem.setDeadline(request.deadline());
        else
            toDoItem.setDeadline("");

        return toDoItemRepository.save(toDoItem);
    }

    public ToDoItem modify(String username, Long id, ToDoItemDTO.UpdateRequest request) {
        ToDoItem toDoItem = toDoItemRepository.findByUsernameAndId(username, id)
                .orElseThrow(() -> new EntityNotFoundException("To-do item not found"));

        if (request.content() != null)
            toDoItem.setContent(request.content());
        if (request.starred() != null)
            toDoItem.setStarred(request.starred());
        if (request.completed() != null)
            toDoItem.setCompleted(request.completed());
        if (request.deadline() != null)
            toDoItem.setDeadline(request.deadline());

        return toDoItemRepository.save(toDoItem);
    }

    public void remove(String username, Long id) {
        ToDoItem toDoItem = toDoItemRepository.findByUsernameAndId(username, id)
                .orElseThrow(() -> new EntityNotFoundException("To-do item not found"));

        toDoItemRepository.delete(toDoItem);
    }
}
