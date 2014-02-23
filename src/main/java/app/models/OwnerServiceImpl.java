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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.wmw.bank.BankValidator;

@Named
public class OwnerServiceImpl implements OwnerService {

  @Inject
  private OwnerDAO ownerDAO;

  @Transactional
  @Override
  public boolean addOwner(String firstName, String lastName, String ssn,
      String email, String phone) {
    OwnerBean owner = new OwnerBean();
    owner.setFirstName(firstName);
    owner.setLastName(lastName);
    owner.setSsn(ssn);
    owner.setEmail(email);
    owner.setPhone(phone);
    if (!BankValidator.validateOwner(owner))
      throw new IllegalStateException("Owner contains invalid value.");
    return ownerDAO.save(owner);
  }

  @Transactional
  @Override
  public List<OwnerBean> getAllOwners() {
    return ownerDAO.findAll();
  }

  @Transactional
  @Override
  public boolean deleteOwner(Integer ownerId) {
    return ownerDAO.removeById(ownerId);
  }

}