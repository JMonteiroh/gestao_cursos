package com.joaomonteiro.gestao_cursos.modules.courses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaomonteiro.gestao_cursos.modules.courses.entity.CourseEntity;
import com.joaomonteiro.gestao_cursos.modules.courses.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<CourseEntity> create(@RequestBody CourseEntity courseEntity){
        courseEntity.setActive(true);   

        CourseEntity saveCourse = courseRepository.save(courseEntity);

        return new ResponseEntity<>(saveCourse, HttpStatus.CREATED);
    }
}
