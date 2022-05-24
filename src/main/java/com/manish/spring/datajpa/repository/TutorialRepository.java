package com.manish.spring.datajpa.repository;

import java.util.List;

import com.manish.spring.datajpa.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    @Override
    Tutorial save(Tutorial tutorial);

    @Override
    List<Tutorial> findAll();

    Tutorial findById(long id);

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}
