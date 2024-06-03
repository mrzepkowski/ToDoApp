package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.entity.Color;
import com.example.ToDoAppBackend.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllColors() {
        return new ResponseEntity<>(colorService.loadAllColors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColorById(@PathVariable Integer id) {
        Color color = colorService.loadById(id);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @GetMapping("/{colorName}")
    public ResponseEntity<?> getColorByName(@PathVariable String colorName) {
        Color color = colorService.loadByName(colorName);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
}
