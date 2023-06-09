package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.Customer;
import rikkei.academy.service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/customer"})
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping
    public String showListCustomer(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("listCustomer", customerList);
        return "customer/list";
    }
    @GetMapping("/{id}")
    public String detailCustomer(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/detail";
    }
    @GetMapping("/create")
    public String showFormCreate(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/create";
    }
    @PostMapping("/create")
    public String actionCreate(Customer customer, RedirectAttributes rs){
        customerService.save(customer);
        rs.addAttribute("validate", "Create customer success");
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/update";
    }
    @PostMapping("/update")
    public String actionUpdate(Customer customer, RedirectAttributes rs){
        customerService.save(customer);
        rs.addAttribute("validate", "Update customer success");
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String showFormDelete(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/delete";
    }
    @PostMapping("/delete")
    public String actionDelete(Customer customer, RedirectAttributes rs){
        customerService.deleteById(customer.getId());
        rs.addAttribute("validate", "Delete customer success");
        return "redirect:/";
    }
}
