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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import app.models.AccountDAO;
import app.models.OwnerDAO;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class AccountsControllerIntegrationTest {

  MockMvc mockMvc;

  @Inject
  AccountsController controller;

  @Inject
  AccountDAO accountDAO;

  @Inject
  OwnerDAO ownerDAO;

  @Before
  public void setUp() throws Exception {
    mockMvc = standaloneSetup(controller).build();
  }

  @DatabaseSetup({ "classpath:datasets/owner.xml",
      "classpath:datasets/account.xml", "classpath:datasets/account_owner.xml" })
  @Test
  public
      void getIndex() throws Exception {
    mockMvc.perform(get("/accounts")).andExpect(status().isOk())
        .andExpect(view().name("accounts/index"))
        .andExpect(model().attribute("owners", ownerDAO.findAll()))
        .andExpect(model().attribute("accounts", accountDAO.findAll()));
  }

  @DatabaseSetup("classpath:datasets/owner.xml")
  @Test
  public void postCreate() throws Exception {
    mockMvc
        .perform(
            post("/accounts").param("accountNumber", "1234")
                .param("routingNumber", "5678").param("ownerId", "1")
                .param("ownerId", "2")).andExpect(status().isOk())
        .andExpect(view().name("accounts/index"))
        .andExpect(model().attribute("owners", ownerDAO.findAll()))
        .andExpect(model().attribute("accounts", accountDAO.findAll()));
  }

}
