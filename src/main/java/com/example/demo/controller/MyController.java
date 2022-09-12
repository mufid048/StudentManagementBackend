package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class MyController<LoginRequest> {
    studentRepository studentrepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @GetMapping
    public String getWelcomeMessage(){
        return "<h1> Welcome to the world of Spring Boot!!!</h1>";
    }
    @Autowired
    @GetMapping("/listStudents")
    public List<Student> getAllStudents() {
        return studentrepository.findAll();
    }
//    @GetMapping("/student/{id}")
//    public Student getStudent(@PathVariable Integer id) {
//        return studentrepository.findById(id).get();
//    }
//    @DeleteMapping("/student/{id}") // delete from student where id=?
//    public List<Student> deleteStudent(@PathVariable Integer id) {
//        studentrepository.delete(studentrepository.findById(id).get());
//        return studentrepository.findAll();
//    }
//    @PostMapping("/student") // insert into student values(?, ?, ?)
//    public List<Student> addStudent(@RequestBody Student student) {
//        studentrepository.save(student);
//        return studentrepository.findAll();
//    }
//    @PutMapping("/student/{id}") // update table student set name=? where id=?
//    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
//        Student studentObj = studentrepository.findById(id).get();
//        studentObj.setName(student.getName());
//        studentObj.setAddress(student.getAddress());
//        studentrepository.save(studentObj);
//        return studentrepository.findAll();
//    }
@PostMapping("/login")
public String doLogin(@RequestBody com.example.demo.payload.LoginRequest loginRequest){
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),loginRequest.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return "Login Successful";
}
    @PostMapping("/login")
    public String doLogin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Log in Success!!!";
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Logout");
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Logged out successful";
    }
}
