package com.example.myapplication;

public class Mowazaf {
    private String First_Name ,Second_Name,ID,Password,Gender,Goverment,Email,Phone_number , Status,job,age,FireBase_ID;
    private float countOfrates,totalrates;
    private   float rate;

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

    private boolean is_logged_in;

    public Mowazaf() {
    }
    public String getFireBase_ID() {
        return FireBase_ID;
    }

    public void setFireBase_ID(String fireBase_ID) {
        FireBase_ID = fireBase_ID;
    }

    public Mowazaf(String first_Name, String second_Name, String ID, String password, String gender, String goverment, String email, String phone_number, String status, String job, String age, float rate,float countOfrates,float totalrates) {
        First_Name = first_Name;
        Second_Name = second_Name;
        this.ID = ID;
        Password = password;
        Gender = gender;
        Goverment = goverment;
        Email = email;
        Phone_number = phone_number;
        Status = status;
        this.job = job;
        this.age = age;
        this.rate = rate;
        this.is_logged_in = is_logged_in;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getSecond_Name() {
        return Second_Name;
    }

    public void setSecond_Name(String second_Name) {
        Second_Name = second_Name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getGoverment() {
        return Goverment;
    }

    public void setGoverment(String goverment) {
        Goverment = goverment;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public float getRate() {
        if(countOfrates>0)
            rate = totalrates/countOfrates;
        else
            rate = 0.0F;
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean isIs_loged_in() {
        return is_logged_in;
    }

    public void setIs_loged_in(boolean is_loged_in) {
        this.is_logged_in = is_loged_in;
    }
}
