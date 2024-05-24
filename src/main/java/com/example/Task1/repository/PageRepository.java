package com.example.Task1.repository;

import com.example.Task1.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByStoryId(Long storyId);

    @Query("SELECT MAX(p.pageNumber) FROM Page p WHERE p.story.id = :storyId")
    Optional<Integer> findMaxPageNumberByStoryId(@Param("storyId") Long storyId);
}