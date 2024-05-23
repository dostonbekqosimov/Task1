package com.example.Task1.entity;

import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pages", uniqueConstraints = @UniqueConstraint(columnNames = {"story_id", "page_number"}))
@Data
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @Column(nullable = false)
    private String content;

    @Column(name = "page_number", nullable = false)
    private Integer pageNumber;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dislikes")
    private int dislikes;

    // Getters and Setters
}




