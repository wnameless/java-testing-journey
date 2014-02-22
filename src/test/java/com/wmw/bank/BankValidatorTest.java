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
package com.wmw.bank;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BankValidatorTest {

  @Mock
  Owner owner;

  @Mock
  Account account;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(owner.getFirstName()).thenReturn("John");
    when(owner.getLastName()).thenReturn("Doe");
    when(owner.getSsn()).thenReturn("123-456-7890");
    when(owner.getEmail()).thenReturn(null);
    when(owner.getPhone()).thenReturn(null);
    when(account.getAccountNumber()).thenReturn(12345);
    when(account.getRoutingNumber()).thenReturn(67890);
    when(account.getOwners()).thenReturn(newArrayList(owner));
  }

  @Test
  public void checkIfPrivateConstructorExisted() throws Exception {
    Constructor<BankValidator> c = BankValidator.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(c.getModifiers()));
    c.setAccessible(true);
    c.newInstance();
  }

  @Test
  public void testValidateOwner() {
    assertTrue(BankValidator.validateOwner(owner));
  }

  @Test(expected = NullPointerException.class)
  public void validateOwnerWithNullObjectShouldRaiseException() {
    BankValidator.validateOwner(null);
  }

  @Test
  public void ownerWithBlankFirstNameIsInvalid() {
    when(owner.getFirstName()).thenReturn(null);
    assertFalse(BankValidator.validateOwner(owner));
    when(owner.getFirstName()).thenReturn("   ");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithBlankLastNameIsInvalid() {
    when(owner.getLastName()).thenReturn(null);
    assertFalse(BankValidator.validateOwner(owner));
    when(owner.getLastName()).thenReturn("   ");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithBlankSsnIsInvalid() {
    when(owner.getSsn()).thenReturn(null);
    assertFalse(BankValidator.validateOwner(owner));
    when(owner.getSsn()).thenReturn("   ");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithWrongSsnIsInvalid() {
    when(owner.getSsn()).thenReturn("123456");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithNullOrEmptyEmailIsValid() {
    when(owner.getEmail()).thenReturn(null);
    assertTrue(BankValidator.validateOwner(owner));
    when(owner.getEmail()).thenReturn("");
    assertTrue(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithValidEmailIsValid() {
    when(owner.getEmail()).thenReturn("abc@def.com");
    assertTrue(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithInvalidEmailIsInvalid() {
    when(owner.getEmail()).thenReturn("abc@d$ef.xx");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithNullOrEmptyPhoneIsValid() {
    when(owner.getPhone()).thenReturn(null);
    assertTrue(BankValidator.validateOwner(owner));
    when(owner.getPhone()).thenReturn("");
    assertTrue(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithValidPhoneIsValid() {
    when(owner.getPhone()).thenReturn("7182223333");
    assertTrue(BankValidator.validateOwner(owner));
  }

  @Test
  public void ownerWithInvalidPhoneIsInvalid() {
    when(owner.getPhone()).thenReturn("$$$$$$");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void testValidateAccount() {
    assertTrue(BankValidator.validateAccount(account));
  }

  @Test(expected = NullPointerException.class)
  public void validateAccountWithNullObjectShouldRaiseException() {
    BankValidator.validateAccount(null);
  }

  @Test
  public void accountWithNonPositiveAccountNumberIsInvalid() {
    when(account.getAccountNumber()).thenReturn(0);
    assertFalse(BankValidator.validateAccount(account));
    when(account.getAccountNumber()).thenReturn(-1);
    assertFalse(BankValidator.validateAccount(account));
  }

  @Test
  public void accountWithNonPositiveRoutingNumberIsInvalid() {
    when(account.getRoutingNumber()).thenReturn(0);
    assertFalse(BankValidator.validateAccount(account));
    when(account.getRoutingNumber()).thenReturn(-1);
    assertFalse(BankValidator.validateAccount(account));
  }

  @Test
  public void accountWithoutOwnerIsInvalid() {
    when(account.getOwners()).thenReturn(new ArrayList<Owner>());
    assertFalse(BankValidator.validateAccount(account));
  }

  @Test
  public void accountWithAnyNullOwnerIsInvalid() {
    List<Owner> owners = newArrayList(owner);
    owners.add(null);
    when(account.getOwners()).thenReturn(owners);
    assertFalse(BankValidator.validateAccount(account));
  }

}
