package com.example.Task1.controller;

import com.example.Task1.entity.Page;
import com.example.Task1.entity.PageDTO;
import com.example.Task1.entity.Story;
import com.example.Task1.service.PageService;
import com.example.Task1.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private PageService pageService;


// Implementation of getAllStories
    @GetMapping
    public List<Story> getAllStories() {
        return storyService.getAllStories();
    }

    @GetMapping("{id}")
    public Story getStoryById(@PathVariable Long id){
        return storyService.getStoryById(id);
    }

    @PostMapping("/story")
    public Story addStory(@RequestBody Story story){
        return storyService.addStory(story);
    }

    @PostMapping("/{storyId}/pages")
    public Page addPageToStory(@PathVariable Long storyId, @RequestBody PageDTO pageDTO) {
        return pageService.addPageToStory(storyId, pageDTO);
    }
}