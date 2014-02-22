package app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OWNER")
public class OwnerBean {

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
  private String email = "";

  @Column(name = "PHONE")
  private String phone = "";

}
