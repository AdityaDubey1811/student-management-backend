package com.example.backenddemo.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentDTO {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @Min(value = 1,message = "Age must be atleast 1")
    private int age;
    public StudentDTO(){}
    public StudentDTO(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
}
