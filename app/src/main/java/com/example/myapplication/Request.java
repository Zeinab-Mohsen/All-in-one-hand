    package com.example.myapplication;

    import java.util.ArrayList;

    public class Request {
        private Zobon zobon;
        private String Zobon_first_name,Zobon_second_name, Zobon_phone_number ;
        private float Zobon_rate;
        private String rate , job , goverment , date , time , address , status;
        private boolean is_answered;
        public Request(String job, String government, String date, String time, String address, String status, float request_rate, Boolean is_answered) {
            this.job = job;
            this.goverment = government;
            this.date = date;
            this.time = time;
            this.address = address;
            this.status = status;
            Zobon_rate = request_rate;
            is_answered = is_answered;
        }

        public Request() {
        }

        public Request(Zobon zobon, String rate, String job, String goverment, String date, String time, String address, String status, boolean is_answered) {
            this.zobon = zobon;
            this.rate = rate;
            this.job = job;
            this.goverment = goverment;
            this.date = date;
            this.time = time;
            this.address = address;
            this.status = status;
            this.is_answered = is_answered;
            Zobon_first_name=zobon.getFirst_Name();
            Zobon_second_name=zobon.getSecond_Name();
            Zobon_phone_number=zobon.getPhone_number();
            Zobon_rate=zobon.getRate();
        }

        public Zobon getZobon() {
            return zobon;
        }

        public void setZobon(Zobon zobon) {
            this.zobon = zobon;
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

        public String getZobon_phone_number() {
            return Zobon_phone_number;
        }

        public void setZobon_phone_number(String zobon_phone_number) {
            Zobon_phone_number = zobon_phone_number;
        }

        public float getZobon_rate() {
            return Zobon_rate;
        }

        public void setZobon_rate(float zobon_rate) {
            Zobon_rate = zobon_rate;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getGoverment() {
            return goverment;
        }

        public void setGoverment(String goverment) {
            this.goverment = goverment;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public boolean isIs_answered() {
            return is_answered;
        }

        public void setIs_answered(boolean is_answered) {
            this.is_answered = is_answered;
        }


    }
