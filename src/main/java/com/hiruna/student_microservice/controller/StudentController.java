package com.hiruna.student_microservice.controller;

import com.hiruna.student_microservice.data.Student;
import com.hiruna.student_microservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService stdService;

    @PostMapping(path = "/students")
    public Student createStudent(@RequestBody Student std){
        return stdService.createStudent(std);
    }

    @GetMapping(path = "/students")
    public List<Student> getAllStudents(){
        return stdService.getAllStudents();
    }

    @PutMapping(path = "/students")
    public Student updateStudent(@RequestBody Student std){
        return stdService.updateStudent(std);
    }

    @GetMapping(path = "/students", params={"name"})
    public List<Student> getStudentsByName(@RequestParam String name){
        return stdService.getStudentsByName(name);
    }

    @GetMapping(path = "/students/{id}/exists")
    public ResponseEntity<Boolean> studentExists(@PathVariable int id){
        if (stdService.studentExists(id)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @GetMapping(path = "/students/{id}")
    public Optional<Student> getStudentById(@PathVariable Integer id){
        return stdService.getStudentById(id);
    }

    @DeleteMapping(path = "/students/{id}/delete")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable int id){
        stdService.deleteStudent(id);
        return ResponseEntity.ok(true);
    }
}
