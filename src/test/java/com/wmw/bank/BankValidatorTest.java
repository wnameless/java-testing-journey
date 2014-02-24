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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

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
    doReturn(newArrayList(owner)).when(account).getOwners();
  }

  @Test
  public void constructorShouldBePrivate() throws Exception {
    Constructor<BankValidator> c = BankValidator.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(c.getModifiers()));
    c.setAccessible(true);
    c.newInstance();
  }

  @Test
  public void testValidateWithOwner() {
    assertSame(owner, BankValidator.validate(owner));
  }

  @Test(expected = NullPointerException.class)
  public void validateWithNullOwnerShouldRaiseException() {
    BankValidator.validate((Owner) null);
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithNullFirstNameIsInvalid() {
    when(owner.getFirstName()).thenReturn(null);
    BankValidator.validate(owner);
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithBlankFirstNameIsInvalid() {
    when(owner.getFirstName()).thenReturn("   ");
    BankValidator.validate(owner);
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithNullLastNameIsInvalid() {
    when(owner.getLastName()).thenReturn(null);
    BankValidator.validate(owner);
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithBlankLastNameIsInvalid() {
    when(owner.getLastName()).thenReturn("   ");
    BankValidator.validate(owner);
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithNullSsnIsInvalid() {
    when(owner.getSsn()).thenReturn(null);
    BankValidator.validate(owner);

  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithBlankSsnIsInvalid() {
    when(owner.getSsn()).thenReturn("   ");
    BankValidator.validate(owner);
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithWrongSsnIsInvalid() {
    when(owner.getSsn()).thenReturn("123456");
    BankValidator.validate(owner);
  }

  @Test
  public void ownerWithNullOrEmptyEmailIsValid() {
    when(owner.getEmail()).thenReturn(null);
    assertSame(owner, BankValidator.validate(owner));
    when(owner.getEmail()).thenReturn("");
    assertSame(owner, BankValidator.validate(owner));
  }

  @Test
  public void ownerWithValidEmailIsValid() {
    when(owner.getEmail()).thenReturn("abc@def.com");
    assertSame(owner, BankValidator.validate(owner));
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithInvalidEmailIsInvalid() {
    when(owner.getEmail()).thenReturn("abc@d$ef.xx");
    assertSame(owner, BankValidator.validate(owner));
  }

  @Test
  public void ownerWithNullOrEmptyPhoneIsValid() {
    when(owner.getPhone()).thenReturn(null);
    assertSame(owner, BankValidator.validate(owner));
    when(owner.getPhone()).thenReturn("");
    assertSame(owner, BankValidator.validate(owner));
  }

  @Test
  public void ownerWithValidPhoneIsValid() {
    when(owner.getPhone()).thenReturn("7182223333");
    assertSame(owner, BankValidator.validate(owner));
  }

  @Test(expected = IllegalStateException.class)
  public void ownerWithInvalidPhoneIsInvalid() {
    when(owner.getPhone()).thenReturn("$$$$$$");
    BankValidator.validate(owner);
  }

  @Test
  public void testValidateAccount() {
    assertSame(account, BankValidator.validate(account));
  }

  @Test(expected = NullPointerException.class)
  public void validateWithNullAccountShouldRaiseException() {
    BankValidator.validate((Account) null);
  }

  @Test(expected = IllegalStateException.class)
  public void accountWithNonPositiveAccountNumberIsInvalid() {
    when(account.getAccountNumber()).thenReturn(-1);
    BankValidator.validate(account);
  }

  @Test(expected = IllegalStateException.class)
  public void accountWithNonPositiveRoutingNumberIsInvalid() {
    when(account.getRoutingNumber()).thenReturn(-1);
    BankValidator.validate(account);
  }

  @Test(expected = IllegalStateException.class)
  public void accountWithoutOwnerIsInvalid() {
    doReturn(newArrayList()).when(account).getOwners();
    BankValidator.validate(account);
  }

  @Test(expected = NullPointerException.class)
  public void accountWithNullOwnersIsInvalid() {
    doReturn(null).when(account).getOwners();
    BankValidator.validate(account);
  }

  @Test(expected = IllegalStateException.class)
  public void accountWithAnyNullOwnerIsInvalid() {
    doReturn(newArrayList(owner, null)).when(account).getOwners();
    BankValidator.validate(account);
  }

  @Test
  public void testTryValidateWithValidOwner() {
    assertTrue(BankValidator.tryValidate(owner));
  }

  @Test
  public void testTryValidateWithInvalidOwner() {
    when(owner.getFirstName()).thenReturn(null);
    assertFalse(BankValidator.tryValidate(owner));
  }

  @Test
  public void testTryValidateWithValidAccount() {
    assertTrue(BankValidator.tryValidate(account));
  }

  @Test
  public void testTryValidateWithInvalidAccount() {
    when(account.getAccountNumber()).thenReturn(-1);
    assertFalse(BankValidator.tryValidate(account));
  }

}
