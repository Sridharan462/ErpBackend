package com.example.ErpApp.Controller;

import com.example.ErpApp.Model.Department;
import com.example.ErpApp.Repository.DepartmentRepository;
import com.example.ErpApp.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/addDepartment")
    public String addDepartment(@RequestBody Department department) {
        departmentRepository.save(department);
        return "department added";
    }

}
