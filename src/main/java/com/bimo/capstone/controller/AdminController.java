package com.bimo.capstone.controller;

import com.bimo.capstone.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/admin")
    public String customerList(Model model) {
//        model.addAttribute("allCustomers", customerService.allCustomers());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteCustomer(@RequestParam(required = true, defaultValue = "" ) Long customerId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
//            customerService.deleteCustomer(customerId);
        }
        return "redirect:/admin";
    }
}
