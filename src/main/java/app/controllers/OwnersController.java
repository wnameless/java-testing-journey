package app.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.models.OwnerService;

@Controller
@RequestMapping("/owners")
public class OwnersController {

  @Inject
  private OwnerService ownerService;

  @RequestMapping(method = GET)
  public String index(ModelMap model) {
    model.addAttribute("owners", ownerService.getAllOwners());
    return "owners";
  }

  @RequestMapping(method = POST)
  public String create(ModelMap model,//
      @RequestParam String firstName,//
      @RequestParam String lastName,//
      @RequestParam String ssn,//
      @RequestParam String email,//
      @RequestParam String phone) {

    ownerService.addOwner(firstName, lastName, ssn, email, phone);
    model.addAttribute("owners", ownerService.getAllOwners());
    return "owners";
  }

}
