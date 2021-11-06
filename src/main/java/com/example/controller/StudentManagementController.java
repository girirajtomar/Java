package com.example.controller;

import com.example.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    List<Student> all_students = List.of(new Student(1,"Giriraj Singh Tomar"),
            new Student(2,"Raj Singh Tomar"),
            new Student(3,"Prem Singh Tomar"),
            new Student(4,"Sunita Tomar"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    List<Student> getAllStudents(){
        return all_students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course-write')")
    void regeisterNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('course-write')")
    void deleteStudent(@PathVariable("id") Integer id){
        System.out.println(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('course-write')")
    void updateStudent(@RequestBody Student student,@PathVariable("id") Integer id){
        System.out.println("deleted");
    }
}
