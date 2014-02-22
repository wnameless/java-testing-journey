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
  public boolean equals(Object o) {
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
  public int hashCode() {
    return Objects.hashCode(firstName, lastName, ssn, email, phone);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this).add("FirstName", firstName)
        .add("LastName", lastName).add("SSN", ssn).add("Email", email)
        .add("Phone", phone).toString();
  }

}
