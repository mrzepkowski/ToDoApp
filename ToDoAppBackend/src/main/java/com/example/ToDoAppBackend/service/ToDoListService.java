package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.dto.ToDoListDTO;
import com.example.ToDoAppBackend.entity.Color;
import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.entity.User;
import com.example.ToDoAppBackend.repository.ColorRepository;
import com.example.ToDoAppBackend.repository.ToDoListRepository;
import com.example.ToDoAppBackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {
    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColorRepository colorRepository;

    public List<ToDoList> loadAllByUsername(String username) {
        return toDoListRepository.findAllByUsername(username);
    }

    public List<ToDoList> loadAllByUsernameAndColorId(String username, Integer colorId) {
        return toDoListRepository.findAllByUsernameAndColorId(username, colorId);
    }

    public ToDoList loadByUsernameAndId(String username, Integer id) {
        return toDoListRepository.findByUsernameAndId(username, id)
                .orElseThrow(() -> new EntityNotFoundException("To-do list not found"));
    }

    public ToDoList saveToDoList(String username, ToDoListDTO.Request request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Color color = colorRepository.findByName(request.colorName())
                .orElseThrow(() -> new EntityNotFoundException("Color not found"));

        ToDoList toDoList = new ToDoList();
        toDoList.setTitle(request.title());
        toDoList.setUser(user);
        toDoList.setColor(color);
        return toDoListRepository.save(toDoList);
    }

    public ToDoList modifyByUsernameAndId(String username, Integer id, ToDoListDTO.Request request) {
        ToDoList toDoList = toDoListRepository.findByUsernameAndId(username, id)
                .orElseThrow(() -> new EntityNotFoundException("To-do list not found"));

        if (request.title() != null)
            toDoList.setTitle(request.title());

        if (request.colorName() != null) {
            Color color = colorRepository.findByName(request.colorName())
                    .orElseThrow(() -> new EntityNotFoundException("Color not found"));
            toDoList.setColor(color);
        }

        return toDoListRepository.save(toDoList);
    }

    public void removeByUsernameAndId(String username, Integer id) {
        ToDoList toDoList = toDoListRepository.findByUsernameAndId(username, id)
                .orElseThrow(() -> new EntityNotFoundException("To-do list not found"));
        toDoListRepository.delete(toDoList);
    }
}
