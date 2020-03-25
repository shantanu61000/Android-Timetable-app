package com.hfad.teacher_app1;

public class Member {
    private String fname, lname , email  , phone, password1 ;

    public Member(String fname , String lname , String email , String phone , String password1)
    {
        this.setFname(fname);
        this.setLname(lname);

        this.setEmail(email);
        this.setPhone(phone);
        this.setPassword1(password1);
    }
    public String getFname() {
        return fname;
    }

    public   void setFname(String fname) {
        this.fname = fname ;
    }

    public String getLname() {
        return lname;
    }

    public  void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public  void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword1() {
        return password1;
    }

    public  void setPassword1(String password1) {
        this.password1 = password1 ;
    }


}
