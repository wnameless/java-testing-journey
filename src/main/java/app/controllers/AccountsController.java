/**
 *
 * @author Wei-Ming Wu
 *
 *
 * Copyright 2014 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package app.controllers;

import java.util.List;

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
    return "accounts/index";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String create(ModelMap model,//
      @RequestParam int accountNumber,//
      @RequestParam int routingNumber,//
      @RequestParam(value = "ownerId") List<Integer> ownerIds,//
      HttpServletRequest request) {

    accountService.addAccount(accountNumber, routingNumber, ownerIds);
    model.addAttribute("owners", ownerService.getAllOwners());
    model.addAttribute("accounts", accountService.getAllAccounts());
    return "accounts/index";
  }

}
