package com.example.myapplication;

public class Zobon {
    private String First_Name , Second_Name , ID , Password , Gender , Goverment , Email , Phone_number , age,FireBase_ID;
    private float countOfrates,totalrates;
    private float rate;

    private boolean is_loged_in;

    public float getCountOfrates() {
        return countOfrates;
    }

    public void setCountOfrates(float countOfrates) {
        this.countOfrates = countOfrates;
    }

    public float getTotalrates() {
        return totalrates;
    }

    public void setTotalrates(float totalrates) {
        this.totalrates = totalrates;
    }

    public Zobon() {
    }

    public String getFireBase_ID() {
        return FireBase_ID;
    }

    public void setFireBase_ID(String fireBase_ID) {
        FireBase_ID = fireBase_ID;
    }

    public Zobon(String first_Name, String second_Name, String ID, String password, String gender, String goverment, String email, String phone_number, String age, float rate,float countOfrates,float totalrates) {
        First_Name = first_Name;
        Second_Name = second_Name;
        this.ID = ID;
        Password = password;
        Gender = gender;
        Goverment = goverment;
        Email = email;
        Phone_number = phone_number;
        this.age = age;
        this.rate = rate;
        this.is_loged_in = is_loged_in;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setSecond_Name(String second_Name) {
        Second_Name = second_Name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setGoverment(String goverment) {
        Goverment = goverment;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setIs_loged_in(boolean is_loged_in) {
        this.is_loged_in = is_loged_in;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getSecond_Name() {
        return Second_Name;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return Password;
    }

    public String getGender() {
        return Gender;
    }

    public String getGoverment() {
        return Goverment;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public String getAge() {
        return age;
    }

    public float getRate() {
        if(countOfrates>0)
        rate = totalrates/countOfrates;
        else
            rate = 0.0F;
        return rate;
    }

    public boolean isIs_loged_in() {
        return is_loged_in;
    }
}
