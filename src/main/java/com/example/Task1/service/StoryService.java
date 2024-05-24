package com.example.Task1.service;

import com.example.Task1.entity.Page;
import com.example.Task1.entity.Story;
import com.example.Task1.entity.StoryDTO;
import com.example.Task1.handler.ResourceNotFoundException;
import com.example.Task1.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.ClassFormatException;
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
        if (storyRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Story not found");
        }
        return storyRepository.findById(id).get();
    }


    @Transactional
    public Story addStory(StoryDTO storyDTO) {

        if(storyDTO.getContent() ==null || storyDTO.getTitle() == null){
            throw new ClassFormatException("Title or content is missing");
        }

        Story story = new Story();
        story.setTitle(storyDTO.getTitle());
        story.setContent(storyDTO.getContent());

        return storyRepository.save(story);
    }
}