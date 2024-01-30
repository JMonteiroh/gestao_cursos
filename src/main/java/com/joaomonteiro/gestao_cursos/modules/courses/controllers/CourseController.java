package com.joaomonteiro.gestao_cursos.modules.courses.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaomonteiro.gestao_cursos.modules.courses.entity.CourseEntity;
import com.joaomonteiro.gestao_cursos.modules.courses.repository.CourseRepository;

@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/")
    public ResponseEntity<CourseEntity> create(@RequestBody CourseEntity courseEntity){  

        CourseEntity saveCourse = courseRepository.save(courseEntity);

        return new ResponseEntity(saveCourse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseEntity>> show(
        @RequestParam(required = false, defaultValue = "true") Boolean active
    ) {
        List<CourseEntity> courses;

        if (active) {
            courses = courseRepository.findByActiveTrue();
        }else {
            courses = courseRepository.findAll();
        }
        return new ResponseEntity(courses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> update(@PathVariable Long id, @RequestBody CourseEntity updated) {
        
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);

        if(optionalCourse.isPresent()) {
            CourseEntity existingCourse = optionalCourse.get();

            existingCourse.setName(updated.getName());
            existingCourse.setCategory(updated.getCategory());

            if (updated.isActive() != existingCourse.isActive()) {
                existingCourse.setActive(updated.isActive());
                existingCourse.setUpdated_at(new Date());
            }

            CourseEntity savedCourse = courseRepository.save(existingCourse);

            return new ResponseEntity<>(savedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseEntity> delete(@PathVariable Long id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            courseRepository.deleteById(id);
            return new ResponseEntity("Curso removido com sucesso.", HttpStatus.OK);
        }else {
            return new ResponseEntity("Curso não encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}
