package com.example.backenddemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backenddemo.entity.Student;
import java.util.List;
import com.example.backenddemo.entity.User;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByNameContainingIgnoreCase(String name);

}
