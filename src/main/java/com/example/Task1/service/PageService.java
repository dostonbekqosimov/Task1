package com.example.Task1.service;

import com.example.Task1.entity.Page;
import com.example.Task1.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    @Autowired
    private PageRepository pageRepository;

    public List<Page> getAllPagesByStoryId(Long storyId) {

        return pageRepository.findByStoryId(storyId);
    }

    public List<Page> getAllPages() {

        return pageRepository.findAll();
    }

    public void incrementLikes(Long pageId) {
        Page page = pageRepository.findById(pageId).orElse(null);
        if (page != null) {
            page.setLikes(page.getLikes() + 1);
            pageRepository.save(page);
        }
    }

    public void incrementDislikes(Long pageId) {
        Page page = pageRepository.findById(pageId).orElse(null);
        if (page != null) {
            page.setDislikes(page.getDislikes() + 1);
            pageRepository.save(page);
        }
    }

    // getReactions for one specific page

    public int getLikesForPage(Long pageId) {
        Page page = pageRepository.findById(pageId).orElse(null);
        return page != null ? page.getLikes() : 0;
    }

    public int getDislikesForPage(Long pageId) {
        Page page = pageRepository.findById(pageId).orElse(null);
        return page != null ? page.getDislikes() : 0;
    }
}