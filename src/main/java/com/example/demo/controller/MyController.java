package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class MyController {
    studentRepository studentrepository;
    @Autowired
    @GetMapping("/listStudents")
    public List<Student> getAllStudents() {
        return studentrepository.findAll();
    }
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentrepository.findById(id).get();
    }
    @DeleteMapping("/student/{id}") // delete from student where id=?
    public List<Student> deleteStudent(@PathVariable Integer id) {
        studentrepository.delete(studentrepository.findById(id).get());
        return studentrepository.findAll();
    }
    @PostMapping("/student") // insert into student values(?, ?, ?)
    public List<Student> addStudent(@RequestBody Student student) {
        studentrepository.save(student);
        return studentrepository.findAll();
    }
    @PutMapping("/student/{id}") // update table student set name=? where id=?
    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        Student studentObj = studentrepository.findById(id).get();
        studentObj.setName(student.getName());
        studentObj.setAddress(student.getAddress());
        studentrepository.save(studentObj);
        return studentrepository.findAll();
    }
}
