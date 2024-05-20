package cmc.sp.webprac.controllers;

import cmc.sp.webprac.dao.*;
import cmc.sp.webprac.enums.AccountStatus;
import cmc.sp.webprac.models.Account;
import cmc.sp.webprac.models.Service;
import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;

@Controller
public class AccountsController {

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    IndividualClientDAO individualClient;

    @Autowired
    EntityClientDAO entityClient;

    @Autowired
    ConnectedServicesDAO connectedServicesDAO;

    @GetMapping("/account")
    String account(@RequestParam Integer account_id, Model model) {
        var account = accountDAO.getById(account_id);
        var individual = account.getIndividual_client();
        var entity = account.getEntity_client();
        model.addAttribute("account", account);
        model.addAttribute("client_type", individual != null ? 0 : 1);
        model.addAttribute("client_id", individual != null ? individual.getClient_id() : entity.getClient_id());
        //model.addAttribute("connectedServicesDAO", connectedServicesDAO);
        return "account";
    }

    @GetMapping("/add_account")
    String addAccount(@RequestParam Integer client_id, @RequestParam Integer client_type, Model model) {
        Account account = new Account();
        if (client_type == 0) {
            account.setIndividual_client(individualClient.getById(client_id));
        }
        if (client_type == 1) {
            account.setEntity_client(entityClient.getById(client_id));
        }
        account.setBalance(BigDecimal.valueOf(0.0));
        account.setStatus(AccountStatus.ACTIVE);
        account.setCredit_max(BigDecimal.valueOf(100));
        //account.setCredit_interval(new PGInterval(0, 1, 0, 0, 0, 0));
        account.setCreation_time(Timestamp.from(Instant.now()));
        accountDAO.save(account);
        return String.format("redirect:/account?account_id=%d", account.getAccount_id());
    }

    @GetMapping("/remove_account")
    String removeAccount(@RequestParam Integer account_id, Model model) {
        var account = accountDAO.getById(account_id);
        var individualHolder = account.getIndividual_client();
        var entityHolder = account.getEntity_client();
        var id = 0;
        var type = 0;
        if (individualHolder == null) {
            type = 1;
            id = entityHolder.getClient_id();
        }
        if (entityHolder == null) {
            id = individualHolder.getClient_id();
        }

        accountDAO.delete(account);
        return String.format("redirect:/client?client_id=%d&client_type=%d", id, type);
    }

    @GetMapping("/disconnect_service")
    String disconnectService(@RequestParam Integer connection_id, Model model) {
        var acc_id = connectedServicesDAO.getById(connection_id).getAccount().getAccount_id();
        connectedServicesDAO.deleteById(connection_id);
        return String.format("redirect:/account?account_id=%d", acc_id);
    }

}
