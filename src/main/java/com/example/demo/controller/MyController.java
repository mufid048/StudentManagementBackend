package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@CrossOrigin(origins="https://app-dummy123.herokuapp.com")
public class MyController {

//    List<Student> students = new ArrayList<>(
//            Arrays.asList(
//                    new Student(1, "Tom", "US"),
//                    new Student(2, "Harry", "Canada"),
//                    new Student(3, "Nick", "UK")
//            )
//    );


//    @GetMapping("/listStudents")
//    public List<Student> getAllStudents(){
//        return students;
//    }
}
