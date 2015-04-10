package ca1;

public class Garage {
       
    private int garageID;
    private String name;
    private String address;
    private String phoneNo;
    private String nameOfGarage;
    private String manager;

    public Garage(int id, String n, String a, String pn, String nog, String m) {
        this.garageID = id;
        this.name = n;
        this.address = a;
        this.phoneNo = pn;
        this.nameOfGarage = nog;
        this.manager = m;

    }

    public Garage(String n, String a, String pn, String nog, String m) {
        this(-1, n, a, pn, nog, m);
    }

    public int getGarageID() {
        return garageID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    
        public String getNameOfGarage() {
        return nameOfGarage;
    }
        
    public String getManager() {
        return manager;
    }
    
    /* Set Methods */

    public void setGarageID(int id) {
        this.garageID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
        
    public void setNameOfGarage(String nameOfGarage) {
        this.nameOfGarage = nameOfGarage;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

                
    }
