package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {
    @Autowired
    private ToDoListRepository toDoListRepository;

    public List<ToDoList> loadAll() {
        return toDoListRepository.findAll();
    }

    public List<ToDoList> loadAllByUsername(String username) {
        return toDoListRepository.findAllByUsername(username);
    }

    public List<ToDoList> loadAllByUsernameAndColorId(String username, Integer colorId) {
        return toDoListRepository.findAllByUsernameAndColorId(username, colorId);
    }

    public ToDoList loadById(Integer id) {
        return toDoListRepository.findById(id).get();
    }

    public ToDoList saveToDoList(ToDoList toDoList) {
        return toDoListRepository.saveAndFlush(toDoList);
    }

    public int modifyToDoListTitleById(String newTitle, Integer id) {
        return toDoListRepository.setToDoListTitleById(newTitle, id);
    }

    public void removeToDoListById(Integer id) {
        toDoListRepository.deleteById(id);
    }
}
