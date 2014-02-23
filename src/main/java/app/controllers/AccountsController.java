package app.controllers;

import static net.sf.rubycollect4j.RubyCollections.newRubyArray;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.rubycollect4j.block.TransformBlock;

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
      @RequestParam int ownerId,//
      HttpServletRequest request) {

    String[] rawOwnerIds = request.getParameterValues("ownerId");
    Integer[] ownerIds =
        newRubyArray(rawOwnerIds).map(new TransformBlock<String, Integer>() {

          @Override
          public Integer yield(String item) {
            return Integer.valueOf(item);
          }

        }).toArray(new Integer[rawOwnerIds.length]);
    accountService.addAccount(accountNumber, routingNumber, ownerIds);
    model.addAttribute("owners", ownerService.getAllOwners());
    model.addAttribute("accounts", accountService.getAllAccounts());
    return "accounts";
  }

}
