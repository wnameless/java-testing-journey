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
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public final class BankValidator {

  private BankValidator() {}

  public static boolean validateOwner(Owner owner) {
    checkNotNull(owner, "Owner can't be null.");

    if (isBlank(owner.getFirstName()) || isBlank(owner.getLastName())
        || isBlank(owner.getSsn()))
      return false;

    if (Pattern.compile("^\\d{3}[- ]?\\d{2,3}[- ]?\\d{4}$")
        .matcher(owner.getSsn()).find())
      return false;

    if (!isNullOrEmpty(owner.getEmail())
        && !EmailValidator.getInstance().isValid(owner.getEmail()))
      return false;

    if (!isNullOrEmpty(owner.getPhone())) {
      boolean isValid = false;
      try {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        isValid =
            phoneUtil.isValidNumber(phoneUtil.parse(owner.getPhone(), "US"));
      } catch (NumberParseException e) {}
      return isValid;
    }

    return true;
  }

  public static boolean validateAccount(Account account) {
    checkNotNull(account, "Account can't be null.");

    if (account.getAccountNumber() <= 0)
      return false;

    if (account.getRoutingNumber() <= 0)
      return false;

    if (account.getOwners().size() < 1 || account.getOwners().contains(null))
      return false;

    return true;
  }

}
