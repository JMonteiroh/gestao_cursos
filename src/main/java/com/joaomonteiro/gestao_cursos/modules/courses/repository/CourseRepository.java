package com.joaomonteiro.gestao_cursos.modules.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaomonteiro.gestao_cursos.modules.courses.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}
