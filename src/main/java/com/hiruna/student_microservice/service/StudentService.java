package com.hiruna.student_microservice.service;

import com.hiruna.student_microservice.data.StudentInterface;
import com.hiruna.student_microservice.data.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentInterface stdRepo;

    @Autowired
    private KafkaTemplate<String, Student> StudentKafkaTemplate;

    public Student createStudent(Student std){
        Student temp_std =  stdRepo.save(std);
        StudentKafkaTemplate.send("student-events", "StudentCreated", temp_std);

        return temp_std;
    }

    public List<Student> getAllStudents(){
        return stdRepo.findAll();
    }

    public Student updateStudent(Student std){
        Student temp_std = stdRepo.save(std);
        StudentKafkaTemplate.send("student-events", "StudentUpdated",temp_std);

        return temp_std;
    }

    public List<Student> getStudentsByName(String name){
        return stdRepo.findByName(name);
    }

    public Boolean studentExists(int id){
        return stdRepo.existsById(id);
    }

    public Optional<Student> getStudentById(int id){
        Optional<Student> std =  stdRepo.findById(id);

        if (std.isPresent()){
            StudentKafkaTemplate.send("student-events", "StudentRetrieved", std.get());
        }

        return std;
    }

    public void deleteStudent(int id){
        Optional<Student> std = stdRepo.findById(id);
        if (std.isPresent()){
            stdRepo.delete(std.get());
            StudentKafkaTemplate.send("student-events", "StudentDeleted", std.get());
        }

    }
}
