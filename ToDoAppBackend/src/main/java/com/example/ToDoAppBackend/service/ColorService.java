package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.entity.Color;
import com.example.ToDoAppBackend.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color findByName(String name) {
        return colorRepository.findByName(name);
    }
}
