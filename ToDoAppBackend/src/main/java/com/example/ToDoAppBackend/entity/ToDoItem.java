package com.example.ToDoAppBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ToDoItems")
public class ToDoItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private ToDoList toDoList;

    private boolean isStarred = false;

    private boolean isCompleted = false;

    private String deadline;

    public ToDoItem(int id, String content, String deadline) {
        this.id = id;
        this.content = content;
        this.deadline = deadline;
    }
}
