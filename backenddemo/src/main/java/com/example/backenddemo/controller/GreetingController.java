package com.example.backenddemo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.backenddemo.service.StudentService;
import java.util.List;
import com.example.backenddemo.dto.StudentDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@RestController
public class    GreetingController {
    @GetMapping("/greet")
    public String greet(@RequestParam String name){
        return "Hello " + name;
    }
    @GetMapping("/greet/{name}")
    public String greetPath(@PathVariable String name){
        return "hello " + name;
    }
    private final StudentService studentService;
    public GreetingController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO){
        StudentDTO saved = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(saved);

    }
    @GetMapping("/students")
    public List<StudentDTO> getStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/students/{id}")
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }
    @PutMapping("/students/{id}")
    public StudentDTO updateStudent(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO){
        return studentService.updateStudent(id,studentDTO);
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "student deleted successfully";
    }
}
