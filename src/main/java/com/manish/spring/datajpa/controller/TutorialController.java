package com.manish.spring.datajpa.controller;

import com.manish.spring.datajpa.model.Tutorial;
import com.manish.spring.datajpa.repository.TutorialReadRepository;
import com.manish.spring.datajpa.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    private TutorialReadRepository tutorialReadRepository;

    @GetMapping("/test")
    public String test(){
        return "Welcome to Service";
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title,
                                                          @RequestParam String type) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            if ("master".equals(type)) {
                if (title == null)
                    tutorialRepository.findAll().forEach(tutorials::add);
                else
                    tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
            } else {
                if (title == null)
                    tutorialReadRepository.findAll().forEach(tutorials::add);
                else
                    tutorialReadRepository.findByTitleContaining(title).forEach(tutorials::add);
            }
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id,
                                                    @RequestParam String type) {
        Tutorial tutorialData;
        if ("master".equals(type)) {
            tutorialData = tutorialRepository.findById(id);
        } else {
            tutorialData = tutorialReadRepository.findById(id);
        }
        if (tutorialData != null) {
            return new ResponseEntity<>(tutorialData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial,
                                                   @RequestParam String type) {
        try {
            Tutorial _tutorial;
            if ("master".equals(type)) {
                _tutorial = tutorialRepository
                        .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            } else {
                _tutorial = tutorialReadRepository
                        .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            }
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
