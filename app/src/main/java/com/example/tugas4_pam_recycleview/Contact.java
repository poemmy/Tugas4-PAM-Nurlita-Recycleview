package com.example.tugas4_pam_recycleview;

public class Contact {
    private String name;
    private String phoneNumber;
    private int profilePicture;

    public Contact(String name, String phoneNumber, int profilePicture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

}


