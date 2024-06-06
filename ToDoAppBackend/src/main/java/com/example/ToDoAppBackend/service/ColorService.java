package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.entity.Color;
import com.example.ToDoAppBackend.repository.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> loadAllColors() {
        return colorRepository.findAll();
    }

    public Color loadByName(String name) {
        return colorRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Color not found"));
    }

    public Color loadById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Color not found"));
    }
}
