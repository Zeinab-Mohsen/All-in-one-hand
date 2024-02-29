package com.example.myapplication;

public class mowazaf_done_requests {
    private Request request;
    private Zobon zobon = new Zobon();
    private Float Zobon_rate;
    private Boolean Zobon_is_rated=false;
    private String Zobon_first_name,Zobon_second_name,Zobon_email,Zobon_id,Zobon_phone_numbeer,Request_job,Request_government,Request_date,Request_time,Request_address;
    private String Mowazaf_id;

    public mowazaf_done_requests(Request request , Zobon zobon)
    {
        Zobon_first_name = zobon.getFirst_Name();
        Zobon_second_name = zobon.getSecond_Name();
        Zobon_rate = zobon.getRate();
        Zobon_email = zobon.getEmail();
        Zobon_id = zobon.getID();
        Zobon_phone_numbeer = zobon.getPhone_number();
        Request_job = request.getJob();
        Request_government = request.getGoverment();
        Request_date = request.getDate();
        Request_time = request.getTime();
        Request_address = request.getAddress();
    }

    public mowazaf_done_requests() {
    }

    public mowazaf_done_requests(Request request, Zobon zobon, Float zobon_rate, Boolean zobon_is_rated, String zobon_first_name, String zobon_second_name, String zobon_job, String zobon_email, String zobon_id, String zobon_phone_numbeer, String request_job, String request_government, String request_date, String request_time, String request_address, String mowazaf_id) {
        this.request = request;
        this.zobon = zobon;
        Zobon_rate = zobon_rate;
        Zobon_is_rated = zobon_is_rated;
        Zobon_first_name = zobon_first_name;
        Zobon_second_name = zobon_second_name;
        Zobon_email = zobon_email;
        Zobon_id = zobon_id;
        Zobon_phone_numbeer = zobon_phone_numbeer;
        Request_job = request_job;
        Request_government = request_government;
        Request_date = request_date;
        Request_time = request_time;
        Request_address = request_address;
        Mowazaf_id = mowazaf_id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Zobon getZobon() {
        return zobon;
    }

    public void setZobon(Zobon zobon) {
        this.zobon = zobon;
    }

    public Float getZobon_rate() {
        return Zobon_rate;
    }

    public void setZobon_rate(Float zobon_rate) {
        Zobon_rate = zobon_rate;
    }

    public Boolean getZobon_is_rated() {
        return Zobon_is_rated;
    }

    public void setZobon_is_rated(Boolean zobon_is_rated) {
        Zobon_is_rated = zobon_is_rated;
    }

    public String getZobon_first_name() {
        return Zobon_first_name;
    }

    public void setZobon_first_name(String zobon_first_name) {
        Zobon_first_name = zobon_first_name;
    }

    public String getZobon_second_name() {
        return Zobon_second_name;
    }

    public void setZobon_second_name(String zobon_second_name) {
        Zobon_second_name = zobon_second_name;
    }

    public String getZobon_email() {
        return Zobon_email;
    }

    public void setZobon_email(String zobon_email) {
        Zobon_email = zobon_email;
    }

    public String getZobon_id() {
        return Zobon_id;
    }

    public void setZobon_id(String zobon_id) {
        Zobon_id = zobon_id;
    }

    public String getZobon_phone_numbeer() {
        return Zobon_phone_numbeer;
    }

    public void setZobon_phone_numbeer(String zobon_phone_numbeer) {
        Zobon_phone_numbeer = zobon_phone_numbeer;
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

    public String getMowazaf_id() {
        return Mowazaf_id;
    }

    public void setMowazaf_id(String mowazaf_id) {
        Mowazaf_id = mowazaf_id;
    }

    public void setAll() {
        Zobon_first_name = zobon.getFirst_Name();
        Zobon_second_name = zobon.getSecond_Name();
        Zobon_rate = zobon.getRate();
        Zobon_email = zobon.getEmail();
        Zobon_id = zobon.getID();
        Zobon_phone_numbeer = zobon.getPhone_number();
        Request_job = request.getJob();
        Request_government = request.getGoverment();
        Request_date = request.getDate();
        Request_time = request.getTime();
        Request_address = request.getAddress();
    }
}
