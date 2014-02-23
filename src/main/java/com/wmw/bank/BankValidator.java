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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public final class BankValidator {

  private static EmailValidator emailValidator = EmailValidator.getInstance();
  private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

  private BankValidator() {}

  public static <T extends Owner> T validate(T owner) {
    checkNotNull(owner, "Owner can't be null.");
    checkState(!isBlank(owner.getFirstName()), "First name can't be blank.");
    checkState(!isBlank(owner.getLastName()), "Last name can't be blank.");
    checkState(!isBlank(owner.getSsn()), "SSN can't be blank.");
    Pattern ssnRegEx = Pattern.compile("^\\d{3}[- ]?\\d{2,3}[- ]?\\d{4}$");
    checkState(ssnRegEx.matcher(owner.getSsn()).find(), "Invalid SSN.");

    if (!isNullOrEmpty(owner.getEmail()))
      checkState(emailValidator.isValid(owner.getEmail()), "Invalid email.");

    if (!isNullOrEmpty(owner.getPhone())) {
      boolean isValid = false;
      try {
        isValid =
            phoneUtil.isValidNumber(phoneUtil.parse(owner.getPhone(), "US"));
      } catch (NumberParseException e) {}
      checkState(isValid, "Invalid phone.");
    }

    return owner;
  }

  public static <T extends Account> T validate(T account) {
    checkNotNull(account, "Account can't be null.");
    checkState(account.getAccountNumber() > 0,
        "Account number must be grater than 0.");
    checkState(account.getRoutingNumber() > 0,
        "Routing number must be grater than 0.");
    List<? extends Owner> owners = account.getOwners();
    checkNotNull(owners, "Owners can't be null.");
    checkState(!owners.isEmpty() && !owners.contains(null),
        "At least 1 owner required and every owner can't be null.");
    return account;
  }

  public static boolean tryValidate(Owner owner) {
    try {
      validate(owner);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean tryValidate(Account account) {
    try {
      validate(account);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

}
