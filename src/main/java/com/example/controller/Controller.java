package com.example.controller;

import com.example.model.Student;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/")
public class Controller {

    List<Student> all_students = List.of(new Student(1,"Giriraj Singh Tomar"),
            new Student(2,"Raj Singh Tomar"),
            new Student(3,"Prem Singh Tomar"),
            new Student(4,"Sunita Tomar"));

    @GetMapping("students")
    public CollectionModel<EntityModel<Student>> getStudents(){
        List<EntityModel<Student>> entityModels =
                all_students.stream()
                        .map(e -> EntityModel.of(e,linkTo(methodOn(Controller.class).getStudent(e.getId())).withRel("students")))
                        .collect(Collectors.toList());
        return CollectionModel.of(entityModels,
                linkTo(methodOn(Controller.class).getStudents()).withSelfRel());
    }


    @GetMapping("students/{id}")
    public EntityModel<Student> getStudent(@PathVariable int id){
        return all_students.stream()
                .filter(e -> e.getId()==id)
                .map(e -> EntityModel.of(e,
                        linkTo(methodOn(Controller.class).getStudent(id)).withSelfRel()
                ))
                .findFirst()
                .get();
    }
}
