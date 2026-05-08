package com.example.backenddemo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.backenddemo.service.StudentService;
import java.util.List;
import com.example.backenddemo.dto.StudentDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class GreetingController {
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
    public ResponseEntity<Page<StudentDTO>> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sortBy));
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO){
        StudentDTO updated = studentService.updateStudent(id,studentDTO);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("student deleted successfully");
    }
    @GetMapping("/students/search")
    public ResponseEntity<List<StudentDTO>> searchStudents(@RequestParam String name){
        return ResponseEntity.ok(studentService.searchStudentsByName(name));
    }
}
