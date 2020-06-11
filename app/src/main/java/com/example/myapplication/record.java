package com.example.myapplication;

public class record {
    String name;
    String phone;
    String address;
    protected
    record()
    {}
    record(String name,String phone,String address)
    {
        this.name=name;
        this.address=address;
        this.phone=phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

}
