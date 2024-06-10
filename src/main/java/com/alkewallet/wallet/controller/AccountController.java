package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.Balance;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String showAccountPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Account> accounts = accountService.getUserAccounts(user.getEmail());
        model.addAttribute("balances", getBalancesFromAccounts(accounts));
        model.addAttribute("content", "account");
        return "index";
    }

    private List<Balance> getBalancesFromAccounts(List<Account> accounts) {
        List<Balance> balances = new ArrayList<>();
        for (Account account : accounts) {
            balances.addAll(account.getBalances());
        }
        return balances;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
