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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.wmw.bank.Account;

@Entity
@Table(name = "ACCOUNT")
public class AccountBean implements Account {

  @Id
  @Column(name = "ID")
  @GeneratedValue
  private Integer id;

  @Column(name = "ACCOUNT_NUMBER", unique = true)
  private int accountNumber;

  @Column(name = "ROUNTING_NUMBER")
  private int routingNumber;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "ACCOUNT_OWNER", joinColumns = { @JoinColumn(
      name = "ACCOUNT_ID", referencedColumnName = "ID") },
      inverseJoinColumns = { @JoinColumn(name = "OWNER_ID",
          referencedColumnName = "ID") })
  private List<OwnerBean> owners;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public int getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override
  public int getRoutingNumber() {
    return routingNumber;
  }

  public void setRoutingNumber(int routingNumber) {
    this.routingNumber = routingNumber;
  }

  @Override
  public List<OwnerBean> getOwners() {
    return owners;
  }

  public void setOwners(List<OwnerBean> owners) {
    this.owners = owners;
  }

  @Override
  public final boolean equals(Object o) {
    if (o instanceof Account) {
      Account account = (Account) o;
      return Objects.equal(accountNumber, account.getAccountNumber())
          && Objects.equal(routingNumber, account.getRoutingNumber())
          && Objects.equal(owners, account.getOwners());
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hashCode(accountNumber, routingNumber, owners);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("accountNumber", accountNumber)
        .add("routingNumber", routingNumber).add("owners", owners).toString();
  }

}
