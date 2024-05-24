package com.example.Task1.service;

import com.example.Task1.entity.Page;
import com.example.Task1.entity.PageDTO;
import com.example.Task1.entity.Story;
import com.example.Task1.repository.PageRepository;
import com.example.Task1.repository.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private StoryRepository storyRepository;

    public List<Page> getAllPagesByStoryId(Long storyId) {

        return pageRepository.findByStoryId(storyId);
    }

    public List<Page> getAllPages() {

        return pageRepository.findAll();
    }

    public int getLikesForPage(Long pageId) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        return page.getLikes();
    }

    public int getDislikesForPage(Long pageId) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        return page.getDislikes();
    }

    @Transactional
    public void incrementLikes(Long pageId) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        page.setLikes(page.getLikes() + 1);
        pageRepository.save(page);
    }

    @Transactional
    public void incrementDislikes(Long pageId) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("Page not found"));
        page.setDislikes(page.getDislikes() + 1);
        pageRepository.save(page);
    }


    public Page addPageToStory(Long storyId, PageDTO pageDTO) {


        Story story = storyRepository.findById(storyId).orElseThrow(() -> new ResourceNotFoundException("Story not found"));

        int nextPageNumber = pageRepository.findMaxPageNumberByStoryId(storyId).orElse(0) + 1;

        Page page = new Page();
        page.setStory(story);
        page.setContent(pageDTO.getContent());
        page.setPageNumber(nextPageNumber);
        page.setLikes(0);
        page.setDislikes(0);

        return pageRepository.save(page);
    }
}