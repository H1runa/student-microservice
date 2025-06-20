package com.hiruna.student_microservice.service;

import com.hiruna.student_microservice.data.StudentInterface;
import com.hiruna.student_microservice.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentInterface stdRepo;

    public Student createStudent(Student std){
        return stdRepo.save(std);
    }

    public List<Student> getAllStudents(){
        return stdRepo.findAll();
    }

    public Student updateStudent(Student std){
        return stdRepo.save(std);
    }

    public List<Student> getStudentsByName(String name){
        return stdRepo.findByName(name);
    }

    public Boolean studentExists(int id){
        return stdRepo.existsById(id);
    }

    public Optional<Student> getStudentById(int id){
        return stdRepo.findById(id);
    }
}
