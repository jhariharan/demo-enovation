package com.enovation.demoenovation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table
public class Employee {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name")
  @NotBlank(message = "First name is mandatory")
  private String firstName;

  @Column(name = "last_name")
  @NotBlank(message = "Last name is mandatory")
  private String lastName;
  @Column(name = "email_address")
  @NotBlank(message = "Email address is mandatory")
  private String emailAddress;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public Employee() {
    //
  }

  public Employee(int id, String firstName, String lastName, String emailAddress) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Employee that = (Employee) o;

    return new EqualsBuilder()
        .append(id, that.id)
        .append(firstName, that.firstName)
        .append(lastName, that.lastName)
        .append(emailAddress, that.emailAddress)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(id)
        .append(firstName)
        .append(lastName)
        .append(emailAddress)
        .toHashCode();
  }

}
