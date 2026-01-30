package com.bank.controller;

import com.bank.model.Admins;
import com.bank.model.Customers;
import com.bank.model.Loan_Applications;
import com.bank.model.Reports;
import com.bank.service.AdminService;
import com.bank.service.CustomerService;
import com.bank.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoanService loanService;

    /*** Admin Login ***/
    @GetMapping("/login")
    public String showLoginPage() {
        return "adminLogin"; // JSP page
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        Admins admin = adminService.login(username, password);

        if (admin != null) {
            model.addAttribute("admin", admin);
            return "adminDashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "adminLogin";
        }
    }

    /*** Admin Dashboard ***/
    @GetMapping("/dashboard")
    public String dashboard() {
        return "adminDashboard";
    }

    /*** Create Customer Account ***/
    @GetMapping("/createAccount")
    public String showCreateAccountPage() {
        return "createAccount"; // JSP: /WEB-INF/views/createAccount.jsp
    }

    @PostMapping("/createCustomer")
    public String createCustomer(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam("accountNo") String accountNo,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "balance", required = false) Double balance,
            @RequestParam(value = "createdAt", required = false) String createdAtStr,
            @RequestParam(value = "updatedAt", required = false) String updatedAtStr,
            Model model) {

        try {
            Customers customer = new Customers();

            if (customerId != null) customer.setId(customerId);

            customer.setAccount_no(accountNo);
            customer.setEmail(email);
            customer.setPassword(password); // will be encoded in CustomerService.register()
            customer.setRegistered(true);

            customer.setCreated_at(createdAtStr != null && !createdAtStr.isEmpty()
                    ? LocalDateTime.parse(createdAtStr)
                    : LocalDateTime.now());

            customer.setUpdated_at(updatedAtStr != null && !updatedAtStr.isEmpty()
                    ? LocalDateTime.parse(updatedAtStr)
                    : LocalDateTime.now());

            boolean created = customerService.register(customer);

            if (created) {
                model.addAttribute("success", "Customer account created successfully!");
            } else {
                model.addAttribute("error", "Email or Account No already exists!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while creating the account.");
        }

        return "createAccount"; // stay on same page to show messages
    }

    /*** Loan Management ***/
    // GET: Show all loan applications (matches /manageLoans.jsp)
    @GetMapping("/manageLoans")
    public String showManageLoansPage(Model model) {
        List<Loan_Applications> loans = loanService.getPendingLoans();
        model.addAttribute("loans", loans);
        return "manageLoans"; // JSP: /WEB-INF/views/manageLoans.jsp
    }

    // POST: Approve or Reject a loan
    @PostMapping("/approveLoan")
    public String approveLoan(@RequestParam("loanId") Long loanId,
                              @RequestParam("status") String status) {
        boolean updated = loanService.updateLoanStatus(loanId, status);
        return updated
                ? "redirect:/admin/manageLoans?success=true"
                : "redirect:/admin/manageLoans?error=true";
    }

    /*** Generate Reports ***/
    @PostMapping("/generateReport")
    public String generateReport(@RequestParam("accountNo") String accountNo, Model model) {

        if (accountNo == null || accountNo.isEmpty()) {
            model.addAttribute("error", "Account number is required to generate a report.");
            return "reports";
        }

        Reports report = loanService.generateCustomerReport(accountNo);
        model.addAttribute("report", report);

        return "reports";
    }

}
