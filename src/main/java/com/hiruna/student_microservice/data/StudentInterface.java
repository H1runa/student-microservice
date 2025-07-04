package com.hiruna.student_microservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInterface extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.name = ?1")
    public List<Student> findByName(String name);
}
