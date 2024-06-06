package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.entity.Color;
import com.example.ToDoAppBackend.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/colors")
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllColors() {
        return new ResponseEntity<>(colorService.loadAllColors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColorById(@PathVariable(name = "id") Long id) {
        Color color = colorService.loadById(id);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @GetMapping("/by-name/{color-name}")
    public ResponseEntity<?> getColorByName(@PathVariable(name = "color-name") String colorName) {
        Color color = colorService.loadByName(colorName);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }
}
