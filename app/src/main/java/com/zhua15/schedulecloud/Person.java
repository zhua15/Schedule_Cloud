package com.zhua15.schedulecloud;

class Person {
    private String username, password, idtype, idcode;


    public Person (String username, String password, String idtype, String idcode ) {
        this.username = username;
        this.password = password;
        this.idtype = idtype;
        this.idcode = idcode;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getIdtype() {
        return this.idtype;
    }
    public String getIdcode() {
        return this.idcode;
    }
}