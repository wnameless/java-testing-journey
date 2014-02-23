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

import static java.util.Collections.emptyList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import app.models.AccountBean;
import app.models.AccountService;
import app.models.OwnerBean;
import app.models.OwnerService;

public class AccountsControllerTest {

  MockMvc mockMvc;

  @InjectMocks
  AccountsController controller;

  @Mock
  OwnerService ownerService;

  @Mock
  AccountService accountService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = standaloneSetup(controller).build();
  }

  @Test
  public void getIndex() throws Exception {
    when(ownerService.getAllOwners()).thenReturn(new ArrayList<OwnerBean>());
    when(accountService.getAllAccounts()).thenReturn(
        new ArrayList<AccountBean>());
    mockMvc.perform(get("/accounts")).andExpect(status().isOk())
        .andExpect(view().name("accounts/index"))
        .andExpect(model().attribute("owners", emptyList()))
        .andExpect(model().attribute("accounts", emptyList()));
  }

  @Test
  public void postCreate() throws Exception {
    when(
        accountService.addAccount(any(int.class), any(int.class),
            any(Integer[].class))).thenReturn(true);
    when(ownerService.getAllOwners()).thenReturn(new ArrayList<OwnerBean>());
    when(accountService.getAllAccounts()).thenReturn(
        new ArrayList<AccountBean>());
    mockMvc
        .perform(
            post("/accounts").param("accountNumber", "1234")
                .param("routingNumber", "5678").param("ownerId", "9"))
        .andExpect(status().isOk()).andExpect(view().name("accounts/index"))
        .andExpect(model().attribute("owners", emptyList()))
        .andExpect(model().attribute("accounts", emptyList()));
  }

}
