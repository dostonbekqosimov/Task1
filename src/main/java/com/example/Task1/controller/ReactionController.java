package com.example.Task1.controller;


import com.example.Task1.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private PageService pageService;


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
