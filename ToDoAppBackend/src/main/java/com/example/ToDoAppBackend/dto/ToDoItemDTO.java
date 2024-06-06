package com.example.ToDoAppBackend.dto;

public class ToDoItemDTO {
    public record SaveRequest(String content, Long toDoListId, String deadline) {}
    public record UpdateRequest(String content, Boolean starred, Boolean completed, String deadline) {}
}
