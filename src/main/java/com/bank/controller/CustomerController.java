package com.bank.controller;

import com.bank.model.Customers;
import com.bank.model.Loan_Applications;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;
import com.bank.service.LoanService;
import com.bank.service.OtpService;
import com.bank.util.EmailSender;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerService customerService;
    @Autowired private AccountService accountService;
    @Autowired private LoanService loanService;
    @Autowired private OtpService otpService;
    @Autowired private EmailSender emailSender;

    /* -------------------- LOGIN & REGISTER -------------------- */

    @GetMapping("/login")
    public String showLoginPage() { return "login"; }

    @GetMapping("/register")
    public String showRegisterPage() { return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam("accountNo") String accountNo,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           HttpSession session,
                           RedirectAttributes redirectAttrs) {

        if (accountNo.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "All fields are required.");
            return "redirect:/customer/register";
        }

        Customers customer = new Customers();
        customer.setAccount_no(accountNo.trim());
        customer.setEmail(email.trim());
        customer.setPassword(password.trim());

        boolean success = customerService.register(customer);

        if (success) {
            Customers saved = customerService.findByAccountNo(accountNo.trim());
            session.setAttribute("resetCustomerId", saved.getId());
            return "redirect:/customer/resetpassword";
        } else {
            redirectAttrs.addFlashAttribute("error", "Account already exists or registration failed.");
            return "redirect:/customer/register";
        }
    }

    /* -------------------- RESET PASSWORD -------------------- */

    @GetMapping("/resetpassword")
    public String showResetPasswordPage(HttpSession session, Model model) {
        if (session.getAttribute("resetCustomerId") == null) {
            model.addAttribute("error", "Session expired. Please register again.");
            return "login";
        }
        return "resetpassword";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestParam("newPassword") String newPassword,
                                HttpSession session,
                                RedirectAttributes redirectAttrs) {

        Long customerId = (Long) session.getAttribute("resetCustomerId");
        if (customerId == null) {
            redirectAttrs.addFlashAttribute("error", "Session expired. Please register again.");
            return "redirect:/customer/register";
        }

        boolean updated = customerService.updatePassword(customerId, newPassword);

        if (updated) {
            session.removeAttribute("resetCustomerId");
            redirectAttrs.addFlashAttribute("message", "Password set successfully. Please login.");
            return "redirect:/customer/login";
        }

        redirectAttrs.addFlashAttribute("error", "Password update failed.");
        return "redirect:/customer/resetpassword";
    }

    /* -------------------- LOGIN WITH OTP -------------------- */

    @PostMapping("/login")
    public String login(@RequestParam("accountNo") String accountNo,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttrs) {

        Customers customer = customerService.login(accountNo.trim(), password.trim());
        if (customer != null) {

            var otp = otpService.generateOtp(customer.getId());
            emailSender.sendOtpEmail(customer.getEmail(), "Your OTP Code", otp.getOtp_code());

            session.setAttribute("pendingCustomerId", customer.getId());
            redirectAttrs.addFlashAttribute("message", "OTP sent to your email.");
            return "redirect:/customer/otp";
        }

        redirectAttrs.addFlashAttribute("error", "Invalid account number or password.");
        return "redirect:/customer/login";
    }

    @GetMapping("/otp")
    public String showOtpPage(HttpSession session, Model model) {
        if (session.getAttribute("pendingCustomerId") == null) {
            model.addAttribute("error", "Session expired. Please login again.");
            return "login";
        }
        return "otp";
    }

    @PostMapping("/validateOtp")
    public String validateOtp(@RequestParam("otp") String otp,
                              HttpSession session,
                              RedirectAttributes redirectAttrs) {

        Long customerId = (Long) session.getAttribute("pendingCustomerId");

        if (customerId == null || !otpService.validateOtp(customerId, otp)) {
            redirectAttrs.addFlashAttribute("error", "Invalid or expired OTP.");
            return "redirect:/customer/otp";
        }

        session.removeAttribute("pendingCustomerId");
        session.setAttribute("customerId", customerId);

        // Get account number from Customer
        Customers customer = customerService.findById(customerId);
        if (customer == null || customer.getAccount_no() == null) {
            redirectAttrs.addFlashAttribute("error", "No account found for your profile.");
            return "redirect:/customer/login";
        }
        String accountNo = customer.getAccount_no();

        // Ensure account exists in AccountService with initial balance 600
        accountService.getAccountIdByCustomerId(customerId, accountNo);

        session.setAttribute("accountNo", accountNo);
        session.setMaxInactiveInterval(3600);

        return "redirect:/customer/dashboard";
    }

    /* -------------------- DASHBOARD -------------------- */

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/customer/login";

        Customers customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customerDashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttrs) {
        session.invalidate();
        redirectAttrs.addFlashAttribute("message", "You have been logged out successfully.");
        return "redirect:/customer/login";
    }

    /* -------------------- LOAN APPLICATION -------------------- */

    @GetMapping("/loanApplication")
    public String showLoanApplicationPage(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/customer/login";

        String accountNo = (String) session.getAttribute("accountNo");
        if (accountNo == null) {
            Customers customer = customerService.findById(customerId);
            if (customer == null || customer.getAccount_no() == null) return "redirect:/customer/login";
            accountNo = customer.getAccount_no();
            session.setAttribute("accountNo", accountNo);
            accountService.getAccountIdByCustomerId(customerId, accountNo);
        }

        model.addAttribute("accountNo", accountNo);
        return "loanApplication";
    }

    @PostMapping("/loan/apply")
    public String applyLoan(@RequestParam("amount") BigDecimal amount,
                            @RequestParam("duration") Integer duration,
                            HttpSession session,
                            RedirectAttributes redirectAttrs) {

        String accountNo = (String) session.getAttribute("accountNo");
        if (accountNo == null) return "redirect:/customer/login";

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 || duration == null || duration <= 0) {
            redirectAttrs.addFlashAttribute("error", "Enter a valid amount and duration.");
            return "redirect:/customer/loanApplication";
        }

        Loan_Applications loan = new Loan_Applications();
        loan.setAccountNo(accountNo);
        loan.setAmount(amount);
        loan.setDuration(duration);
        loan.setStatus("Pending");

        boolean success = loanService.submitLoanApplication(loan);
        if (success) redirectAttrs.addFlashAttribute("message", "Loan application submitted successfully!");
        else redirectAttrs.addFlashAttribute("error", "Unable to process your request. Try again.");

        return "redirect:/customer/loanApplication";
    }

    @GetMapping("/loan/status")
    public String loanStatus(HttpSession session, Model model) {
        String accountNo = (String) session.getAttribute("accountNo");
        if (accountNo == null) return "redirect:/customer/login";

        model.addAttribute("loans", loanService.getLoansByAccountNo(accountNo));
        return "loanStatus";
    }

    /* -------------------- BALANCE & TRANSACTIONS -------------------- */

    @GetMapping("/transactions")
    public String transactions(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/customer/login";

        String accountNo = (String) session.getAttribute("accountNo");
        if (accountNo == null) {
            Customers customer = customerService.findById(customerId);
            accountNo = customer.getAccount_no();
            session.setAttribute("accountNo", accountNo);
        }

        Long accountId = accountService.getAccountIdByCustomerId(customerId, accountNo);
        List<?> transactions = accountService.getLastTransactions(accountId, 10);
        model.addAttribute("transactions", transactions);

        return "miniStatement";
    }

    @GetMapping("/viewBalance")
    public String viewBalance(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/customer/login";

        String accountNo = (String) session.getAttribute("accountNo");
        if (accountNo == null) {
            Customers customer = customerService.findById(customerId);
            accountNo = customer.getAccount_no();
            session.setAttribute("accountNo", accountNo);
        }

        Long accountId = accountService.getAccountIdByCustomerId(customerId, accountNo);
        BigDecimal balance = accountService.getAccountBalance(accountId);
        BigDecimal minBalance = accountService.getMinimumBalance();

        model.addAttribute("accountId", accountId);
        model.addAttribute("balance", balance);
        model.addAttribute("minBalance", minBalance);

        return "viewBalance";
    }

    /* -------------------- FUND TRANSFER -------------------- */

    @GetMapping("/fundTransfer")
    public String showFundTransferPage(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/customer/login";

        String accountNo = (String) session.getAttribute("accountNo");
        if (accountNo == null) {
            Customers customer = customerService.findById(customerId);
            accountNo = customer.getAccount_no();
            session.setAttribute("accountNo", accountNo);
        }

        model.addAttribute("accountNo", accountNo);
        return "fundTransfer";
    }

    @PostMapping("/fundTransfer")
    public String fundTransfer(@RequestParam("fromAccountNo") String fromAccountNo,
                               @RequestParam("toAccountNo") String toAccountNo,
                               @RequestParam("amount") BigDecimal amount,
                               HttpSession session,
                               RedirectAttributes redirectAttrs) {

        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/customer/login";

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            redirectAttrs.addFlashAttribute("error", "Enter a valid amount.");
            return "redirect:/customer/fundTransfer";
        }

        String loggedInAccountNo = (String) session.getAttribute("accountNo");
        if (!loggedInAccountNo.equals(fromAccountNo.trim())) {
            redirectAttrs.addFlashAttribute("error", "You can only transfer funds from your own account!");
            return "redirect:/customer/fundTransfer";
        }

        Long accountId = accountService.getAccountIdByCustomerId(customerId, loggedInAccountNo);
        BigDecimal balance = accountService.getAccountBalance(accountId);
        BigDecimal minBalance = accountService.getMinimumBalance();

        // ✅ check balance AFTER transfer
        BigDecimal remainingBalance = balance.subtract(amount);

        if (remainingBalance.compareTo(minBalance) < 0) {
            redirectAttrs.addFlashAttribute(
                    "error",
                    "Transfer denied. Minimum balance of " + minBalance + " must be maintained."
            );
            return "redirect:/customer/fundTransfer";
        }

        // 🔥 FIX IS HERE
        boolean success = accountService.transferFundsByAccountNo(
                customerId,                // ✅ CORRECT
                fromAccountNo.trim(),
                toAccountNo.trim(),
                amount
        );

        if (success) {
            redirectAttrs.addFlashAttribute("message", "Fund transfer successful!");
        } else {
            redirectAttrs.addFlashAttribute("error", "Insufficient balance or invalid account number.");
        }

        return "redirect:/customer/fundTransfer";
    }
}