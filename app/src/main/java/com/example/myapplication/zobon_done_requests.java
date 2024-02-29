package com.example.myapplication;

public class zobon_done_requests {
    private Request request;
    private Mowazaf mowazaf;
    private Float Mowazaf_rate;
    private Boolean Mowazaf_is_rated=false;
    private String Mowazaf_first_name,Mowazaf_second_name,Mowazaf_job,Mowazaf_email,Mowazaf_id,Mowazaf_phone_numbeer,Request_job,Request_government,Request_date,Request_time,Request_address;
    private String Zobon_id;

    public String getZobon_id() {
        return Zobon_id;
    }

    public void setZobon_id(String zobon_id) {
        Zobon_id = zobon_id;
    }

    public zobon_done_requests(Request request, Mowazaf mowazaf, Float mowazaf_rate, Boolean mowazaf_is_rated, String mowazaf_first_name, String mowazaf_second_name, String mowazaf_job, String mowazaf_email, String mowazaf_id, String mowazaf_phone_numbeer, String request_job, String request_government, String request_date, String request_time, String request_address) {
        this.request = request;
        this.mowazaf = mowazaf;
        Mowazaf_rate = mowazaf_rate;
        Mowazaf_is_rated = mowazaf_is_rated;
        Mowazaf_first_name = mowazaf_first_name;
        Mowazaf_second_name = mowazaf_second_name;
        Mowazaf_job = mowazaf_job;
        Mowazaf_email = mowazaf_email;
        Mowazaf_id = mowazaf_id;
        Mowazaf_phone_numbeer = mowazaf_phone_numbeer;
        Request_job = request_job;
        Request_government = request_government;
        Request_date = request_date;
        Request_time = request_time;
        Request_address = request_address;
    }

    public zobon_done_requests() {
    }

    public zobon_done_requests(Request request , Mowazaf mowazaf)
    {
        Mowazaf_first_name = mowazaf.getFirst_Name();
        Mowazaf_second_name = mowazaf.getSecond_Name();
        Mowazaf_rate = mowazaf.getRate();
        Mowazaf_job = mowazaf.getJob();
        Mowazaf_email = mowazaf.getEmail();
        Mowazaf_id = mowazaf.getID();
        Mowazaf_phone_numbeer = mowazaf.getPhone_number();
        Request_job = request.getJob();
        Request_government = request.getGoverment();
        Request_date = request.getDate();
        Request_time = request.getTime();
        Request_address = request.getAddress();
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Mowazaf getMowazaf() {
        return mowazaf;
    }

    public void setMowazaf(Mowazaf mowazaf) {
        this.mowazaf = mowazaf;
    }

    public Float getMowazaf_rate() {
        return Mowazaf_rate;
    }

    public void setMowazaf_rate(Float mowazaf_rate) {
        Mowazaf_rate = mowazaf_rate;
    }

    public Boolean getMowazaf_is_rated() {
        return Mowazaf_is_rated;
    }

    public void setMowazaf_is_rated(Boolean mowazaf_is_rated) {
        Mowazaf_is_rated = mowazaf_is_rated;
    }

    public String getMowazaf_first_name() {
        return Mowazaf_first_name;
    }

    public void setMowazaf_first_name(String mowazaf_first_name) {
        Mowazaf_first_name = mowazaf_first_name;
    }

    public String getMowazaf_second_name() {
        return Mowazaf_second_name;
    }

    public void setMowazaf_second_name(String mowazaf_second_name) {
        Mowazaf_second_name = mowazaf_second_name;
    }

    public String getMowazaf_job() {
        return Mowazaf_job;
    }

    public void setMowazaf_job(String mowazaf_job) {
        Mowazaf_job = mowazaf_job;
    }

    public String getMowazaf_email() {
        return Mowazaf_email;
    }

    public void setMowazaf_email(String mowazaf_email) {
        Mowazaf_email = mowazaf_email;
    }

    public String getMowazaf_id() {
        return Mowazaf_id;
    }

    public void setMowazaf_id(String mowazaf_id) {
        Mowazaf_id = mowazaf_id;
    }

    public String getMowazaf_phone_numbeer() {
        return Mowazaf_phone_numbeer;
    }

    public void setMowazaf_phone_numbeer(String mowazaf_phone_numbeer) {
        Mowazaf_phone_numbeer = mowazaf_phone_numbeer;
    }

    public String getRequest_job() {
        return Request_job;
    }

    public void setRequest_job(String request_job) {
        Request_job = request_job;
    }

    public String getRequest_government() {
        return Request_government;
    }

    public void setRequest_government(String request_government) {
        Request_government = request_government;
    }

    public String getRequest_date() {
        return Request_date;
    }

    public void setRequest_date(String request_date) {
        Request_date = request_date;
    }

    public String getRequest_time() {
        return Request_time;
    }

    public void setRequest_time(String request_time) {
        Request_time = request_time;
    }

    public String getRequest_address() {
        return Request_address;
    }

    public void setRequest_address(String request_address) {
        Request_address = request_address;
    }
    public void setAll()
    {
        Mowazaf_first_name = mowazaf.getFirst_Name();
        Mowazaf_second_name = mowazaf.getSecond_Name();
        Mowazaf_rate = mowazaf.getRate();
        Mowazaf_job = mowazaf.getJob();
        Mowazaf_email = mowazaf.getEmail();
        Mowazaf_id = mowazaf.getID();
        Mowazaf_phone_numbeer = mowazaf.getPhone_number();
        Request_job = request.getJob();
        Request_government = request.getGoverment();
        Request_date = request.getDate();
        Request_time = request.getTime();
        Request_address = request.getAddress();
    }
}
