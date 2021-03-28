package com.example.mycareer;

public class CollegeData {

    private String collegeName;
    private String collegeDetails;
    private String collegeFee;
    private int collegeImage;

    public CollegeData(String collegeName, String collegeDetails, String collegeFee, int collegeImage) {
        this.collegeName = collegeName;
        this.collegeDetails = collegeDetails;
        this.collegeFee = collegeFee;
        this.collegeImage = collegeImage;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getCollegeDetails() {
        return collegeDetails;
    }

    public String getCollegeFee() {
        return collegeFee;
    }

    public int getCollegeImage() {
        return collegeImage;
    }
}
