package com.example.covid;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Account implements Serializable {
    //Field
    private static final long serialVersionUID = 1L;

    private static String account_id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String gender;
    private String telephoneNumber;
    private String bloodType;
    private int vaccinations;
    private int age;
    private boolean infected;
    private String identificationNumber;

    //Constructor
    Account(){}
    Account(String account_id, String firstname, String lastname, String username, String password, String gender, String telephoneNumber, String bloodType, int vaccinations, int age, boolean infected, String identificationNumber){
        this.account_id = account_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.telephoneNumber = telephoneNumber;
        this.bloodType = bloodType;
        this.vaccinations = vaccinations;
        this.age = age;
        this.infected = infected;
        this.identificationNumber = identificationNumber;
    }

    //Access Method
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public static String getAccount_id() {
        return account_id;
    }
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public int getVaccinations() {
        return vaccinations;
    }
    public void setVaccinations(int vaccinations) {
        this.vaccinations = vaccinations;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isInfected() {
        return infected;
    }
    public void setInfected(boolean infected) {
        this.infected = infected;
    }
}
