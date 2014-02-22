package com.wmw.bank;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
  }

  @Test
  public void testValidateOwner() {
    assertTrue(BankValidator.validateOwner(owner));
  }

  @Test
  public void nullFirstNameIsInvalid() {
    when(owner.getFirstName()).thenReturn(null);
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void blankFirstNameIsInvalid() {
    when(owner.getFirstName()).thenReturn("   ");
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void nullLastNameIsInvalid() {
    when(owner.getLastName()).thenReturn(null);
    assertFalse(BankValidator.validateOwner(owner));
  }

  @Test
  public void blankLastNameIsInvalid() {
    when(owner.getLastName()).thenReturn("   ");
    assertFalse(BankValidator.validateOwner(owner));
  }

}
