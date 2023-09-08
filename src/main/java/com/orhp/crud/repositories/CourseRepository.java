package com.orhp.crud.repositories;

import com.orhp.crud.entitites.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findByName(String course1);
}
