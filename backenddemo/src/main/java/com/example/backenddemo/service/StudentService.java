package com.example.backenddemo.service;

import com.example.backenddemo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.example.backenddemo.entity.Student;
import com.example.backenddemo.repository.StudentRepository;

import com.example.backenddemo.dto.StudentDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }
    public StudentDTO createStudent(StudentDTO studentDTO){
        logger.info("Creating student with name: {}",studentDTO.getName());
        Student student = modelMapper.map(studentDTO,Student.class);
        Student savedStudent = studentRepository.save(student);
        logger.info("Student created with id:{}",savedStudent.getId());
        return modelMapper.map(savedStudent,StudentDTO.class);
    }
    public Page<StudentDTO> getAllStudents(Pageable pageable){
        logger.info("Fetching all students with pagination");
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(student ->
                modelMapper.map(student,StudentDTO.class)
        );
    }
    public StudentDTO getStudentById(Long id){
        logger.info("fetching students according to id: {}",id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return modelMapper.map(student,StudentDTO.class);
    }
    public StudentDTO updateStudent(Long id,StudentDTO studentDTO){
        logger.info("Updating student with id: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        modelMapper.map(studentDTO,student);
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent,StudentDTO.class);
    }
    public void deleteStudent(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student with id {} not found",id);
                    return new ResourceNotFoundException("Student with id" + id + "not found");
                });

        studentRepository.delete(student);
        logger.info("Deleted student with id: {}", id);
    }
    public List<StudentDTO> searchStudentsByName(String name){
        logger.info("Searching students with name:{}" ,name);
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(name);
        return students.stream()
                .map(student -> modelMapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
    }
}
