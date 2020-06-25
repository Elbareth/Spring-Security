package com.example.springSecurity.controller;

import com.example.springSecurity.dto.StudentDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management")
public class ManagmentContoller {
    private static final List<StudentDTO> STUDENT =
            Arrays.asList(
                    new StudentDTO(1,"nazwa1"),
                    new StudentDTO(2,"nazwa2"),
                    new StudentDTO(3,"nazwa3"),
                    new StudentDTO(4,"nazwa4"),
                    new StudentDTO(5,"nazwa5"));
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_TRAINEE')")
    public List<StudentDTO> findAll(){
        return STUDENT;
    }
    @PostMapping
    //@PreAuthorize("hasAuthority('student:write')")
    public StudentDTO create(@RequestBody StudentDTO studentDTO){
        STUDENT.add(studentDTO);
        return studentDTO;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        STUDENT.remove(id - 1);
    }
    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable("id") Integer id, @RequestBody StudentDTO tmp){
        StudentDTO studentDTO = tmp;
        STUDENT.add(id - 1, studentDTO);
        return studentDTO;
    }
}
