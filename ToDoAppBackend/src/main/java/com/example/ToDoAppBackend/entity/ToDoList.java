package com.example.ToDoAppBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ToDoLists")
public class ToDoList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;
}
