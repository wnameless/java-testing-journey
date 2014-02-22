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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.wmw.bank.Owner;

@Entity
@Table(name = "OWNER")
public class OwnerBean implements Owner {

  @Id
  @Column(name = "ID")
  @GeneratedValue
  private Integer id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "SSN", unique = true)
  private String ssn;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PHONE")
  private String phone;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  @Override
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public final boolean equals(Object o) {
    if (o instanceof Owner) {
      Owner owner = (Owner) o;
      return Objects.equal(firstName, owner.getFirstName())
          && Objects.equal(lastName, owner.getLastName())
          && Objects.equal(ssn, owner.getSsn())
          && Objects.equal(email, owner.getEmail())
          && Objects.equal(phone, owner.getPhone());
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hashCode(firstName, lastName, ssn, email, phone);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("firstName", firstName)
        .add("lastName", lastName).add("ssn", ssn).add("email", email)
        .add("phone", phone).toString();
  }

}
