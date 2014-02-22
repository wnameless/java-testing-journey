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

import com.wmw.bank.Owner;

@Entity
@Table(name = "ACCOUNT")
public class AccountBean {

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
  private List<Owner> owners;

}
