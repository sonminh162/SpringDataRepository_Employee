package com.codegym.cms.controllers;

import com.codegym.cms.model.Department;
import com.codegym.cms.model.Employee;
import com.codegym.cms.service.DepartmentService;
import com.codegym.cms.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees")
    public ModelAndView gitListEmployees(@RequestParam("name") Optional<String> name, @RequestParam("dept") Optional<String> dept, Pageable pageable){
        Page<Employee> employees = null;
        if(name.isPresent() && dept.isPresent()){
            if(!name.get().equals("") && dept.get().equals("")){
                employees = employeeService.findAllByName(name.get(),pageable);
            }
            if(name.get().equals("") && !dept.get().equals("")){
                employees = employeeService.findAllByDepartment(dept.get(),pageable);
            }
            if(name.get().equals("") && dept.get().equals("")){
                employees = employeeService.findAll(pageable);
            }
            if(!name.get().equals("") && !dept.get().equals("")){
                Department department = departmentService.findByName(dept.get());
                employees = employeeService.findAllByNameAndDepartment(name.get(),department.getId(),pageable);
            }
        }
        if(!dept.isPresent() && !name.isPresent()){
            employees = employeeService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/employee/list");
        modelAndView.addObject("employees",employees);
        return modelAndView;
    }

    @ModelAttribute("departments")
    public Iterable<Department> departments(){
        return departmentService.findAll();
    }

    @GetMapping("/edit-employee/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        if(employee != null){
            ModelAndView modelAndView  = new ModelAndView("/employee/edit");
            modelAndView.addObject("employee",employee);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee) throws ParseException {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/edit");
        modelAndView.addObject("message","Employee updated successfully");
        return modelAndView;
    }

    @GetMapping("/create-employee")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee",new Employee());
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee) throws ParseException {
        employeeService.save(employee);

        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee",new Employee());
        modelAndView.addObject("message","Created Successfully");
        return modelAndView;
    }

    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Employee employee = employeeService.findById(id);
        if(employee != null){
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee",employee);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public String deleteCustomer(@ModelAttribute("employee") Employee employee){
        employeeService.remove(employee.getId());
        return "redirect:employees";
    }
}
