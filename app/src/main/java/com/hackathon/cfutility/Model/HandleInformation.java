package com.hackathon.cfutility.Model;

public class HandleInformation {
    private String handle_name;
    private String rank,max_rank;
    private int rating,max_rating;
    private String email;
    private String first_name;
    private String last_name;
    private String country;
    private String city;
    private int contribution;
    private long lastOnlineTimeSecond;
    private String img_path;
    private int ac,wa,tle,mle;

    public String getImg_path() {
        return img_path;
    }

    public String getMax_rank() {
        return max_rank;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public String getHandle_name() {
        return handle_name;
    }

    public String getRank() {
        return rank;
    }

    public int getWa() {
        return wa;
    }

    public void setWa(int wa) {
        this.wa = wa;
    }

    public int getTle() {
        return tle;
    }

    public void setTle(int tle) {
        this.tle = tle;
    }

    public int getMle() {
        return mle;
    }

    public void setMle(int mle) {
        this.mle = mle;
    }

    public int getRating() {
        return rating;
    }

    public int getMax_rating() {
        return max_rating;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getContribution() {
        return contribution;
    }

    public long getLastOnlineTimeSecond() {
        return lastOnlineTimeSecond;
    }

    public void setHandle_name(String handle_name) {
        this.handle_name = handle_name;
    }

    public void setMax_rank(String max_rank) {
        this.max_rank = max_rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setMax_rating(int max_rating) {
        this.max_rating = max_rating;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    public void setLastOnlineTimeSecond(long lastOnlineTimeSecond) {
        this.lastOnlineTimeSecond = lastOnlineTimeSecond;
    }
}
