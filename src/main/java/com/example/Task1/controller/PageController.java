package com.example.Task1.controller;

import com.example.Task1.entity.Page;
import com.example.Task1.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageController {
    @Autowired
    private PageService pageService;

    // Implementation of getAllPages
    @GetMapping
    public List<Page> getAllPages(){
        return pageService.getAllPages();
    }

    // pages are related to one story
    @GetMapping("/story/{storyId}")
    public List<Page> getAllPagesByStoryId(@PathVariable Long storyId) {
        return pageService.getAllPagesByStoryId(storyId);
    }



    // bularni yani reactionga tegishli narsalarni alohida qilishim kerak

    @GetMapping("/{pageId}/likes")
    public int getLikesForPage(@PathVariable Long pageId) {
        return pageService.getLikesForPage(pageId);
    }

    @GetMapping("/{pageId}/dislikes")
    public int getDislikesForPage(@PathVariable Long pageId) {
        return pageService.getDislikesForPage(pageId);
    }

    @PutMapping("/{pageId}/like")
    public void likePage(@PathVariable Long pageId) {
        pageService.incrementLikes(pageId);
    }

    @PutMapping("/{pageId}/dislike")
    public void dislikePage(@PathVariable Long pageId) {
        pageService.incrementDislikes(pageId);
    }
}