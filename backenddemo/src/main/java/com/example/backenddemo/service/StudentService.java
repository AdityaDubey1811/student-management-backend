package com.example.backenddemo.service;

import org.springframework.stereotype.Service;
import com.example.backenddemo.entity.Student;
import com.example.backenddemo.repository.StudentRepository;

import com.example.backenddemo.dto.StudentDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public StudentDTO createStudent(StudentDTO studentDTO){
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());

        Student savedStudent = studentRepository.save(student);

        StudentDTO responseDTO = new StudentDTO();
        responseDTO.setName(savedStudent.getName());
        responseDTO.setAge(savedStudent.getAge());

        return responseDTO;
    }
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> new StudentDTO(student.getName(),student.getAge()))
                .collect(Collectors.toList());
    }
    public StudentDTO getStudentById(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return new StudentDTO(student.getName(),student.getAge());
    }
    public StudentDTO updateStudent(Long id,StudentDTO studentDTO){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        Student updatedStudent = studentRepository.save(student);
        return new StudentDTO(updatedStudent.getName(),updatedStudent.getAge());
    }
    public void deleteStudent(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with id" + id + "not found"));
        studentRepository.delete(student);
    }
}
