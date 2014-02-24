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
package app.models;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AccountServiceImplTest {

  @Inject
  AccountService service;

  @Inject
  AccountDAO accountDao;

  @Inject
  OwnerDAO ownerDao;

  OwnerBean owner;

  AccountBean account;

  @Before
  public void setUp() throws Exception {
    owner = new OwnerBean();
    owner.setFirstName("John");
    owner.setLastName("Doe");
    owner.setSsn("123-456-7890");
    ownerDao.save(owner);
    account = new AccountBean();
    account.setAccountNumber(12345);
    account.setRoutingNumber(67890);
    account.setOwners(newArrayList(owner));
    accountDao.save(account);
  }

  @Test
  public void testAddAccount() {
    account = new AccountBean();
    account.setAccountNumber(67890);
    account.setRoutingNumber(12345);
    account.setOwners(newArrayList(owner));
    service.addAccount(67890, 12345, newArrayList(owner.getId()));
    assertEquals(2, accountDao.findAll().size());
    assertTrue(accountDao.findAll().contains(account));
  }

  @Test
  public void testGetAccounts() {
    account = new AccountBean();
    account.setAccountNumber(12345);
    account.setRoutingNumber(67890);
    account.setOwners(newArrayList(owner));
    assertEquals(1, accountDao.findAll().size());
    assertEquals(account, service.getAllAccounts().get(0));
  }

  @Test
  public void testDeleteAccount() {
    AccountBean account = accountDao.findAll().get(0);
    service.deleteAccount(account.getId());
    assertEquals(0, accountDao.findAll().size());
  }

}