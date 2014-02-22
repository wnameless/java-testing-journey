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
