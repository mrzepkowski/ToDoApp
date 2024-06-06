package com.example.ToDoAppBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "toDoListId", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToDoList toDoList;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private boolean starred;

    @Column(nullable = false)
    private boolean completed;

    private String deadline;

    @PrePersist
    void setOwnerUsernameBeforeInsert() {
        starred = false;
        completed = false;
    }
}
