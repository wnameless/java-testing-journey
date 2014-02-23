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
