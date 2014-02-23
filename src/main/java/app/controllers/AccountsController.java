package app.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.models.AccountService;
import app.models.OwnerService;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

  @Inject
  private OwnerService ownerService;

  @Inject
  private AccountService accountService;

  @RequestMapping(method = RequestMethod.GET)
  public String index(ModelMap model) {
    model.addAttribute("owners", ownerService.getAllOwners());
    model.addAttribute("accounts", accountService.getAllAccounts());
    return "accounts";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String create(ModelMap model,//
      @RequestParam int accountNumber,//
      @RequestParam int routingNumber,//
      @RequestParam(value = "ownerId") Integer[] ownerIds,//
      HttpServletRequest request) {

    accountService.addAccount(accountNumber, routingNumber, ownerIds);
    model.addAttribute("owners", ownerService.getAllOwners());
    model.addAttribute("accounts", accountService.getAllAccounts());
    return "accounts";
  }

}
