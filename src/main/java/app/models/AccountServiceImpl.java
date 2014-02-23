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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.wmw.bank.BankValidator;

@Named
public class AccountServiceImpl implements AccountService {

  @Inject
  private AccountDAO accountDAO;

  @Inject
  private OwnerDAO ownerDAO;

  @Transactional
  @Override
  public boolean addAccount(int accountNumber, int routingNumber,
      Integer... ownerIds) {
    OwnerBean[] owners = ownerDAO.find(ownerIds);
    AccountBean account = new AccountBean();
    account.setAccountNumber(accountNumber);
    account.setRoutingNumber(routingNumber);
    account.setOwners(newArrayList(owners));
    return accountDAO.save(BankValidator.validate(account));
  }

  @Transactional
  @Override
  public List<AccountBean> getAllAccounts() {
    return accountDAO.findAll();
  }

  @Transactional
  @Override
  public boolean deleteAccount(Integer accountId) {
    return accountDAO.removeById(accountId);
  }

}