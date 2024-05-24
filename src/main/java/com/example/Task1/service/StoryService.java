package com.example.Task1.service;

import com.example.Task1.entity.Page;
import com.example.Task1.entity.Story;
import com.example.Task1.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public Story getStoryById(Long id) {
        return storyRepository.findById(id).get();
    }


    public Story addStory(Story story) {
        return storyRepository.save(story);
    }
}