package ca1;

public class Bus {
  
    private int busesID;
    private String regNo;
    private String make;
    private String model;
    private String noOfSeats;
    private String engineSize;
    private String dateBusBought;
    private String nextService;
    
    public Bus(int id, String rn, String mk, String md, String nos, String es, String dbb, String ns) {
        this.busesID = id;
        this.regNo = rn;
        this.make = mk;
        this.model = md;
        this.noOfSeats = nos;
        this.engineSize = es;
        this.dateBusBought = dbb;
        this.nextService = ns;
    }

    Bus(String regNo, String make, String model, String noOfSeats, String engineSize, String dateBusBought, String nextService) {
        this(-1, regNo, make, model, noOfSeats, engineSize, dateBusBought, nextService);
    }
    
    public int getbusesID() {
        return busesID;
    }
    
    public void setbusesID(int busesID) {
        this.busesID = busesID;
    }
    
    public String getRegNo() {
        return regNo;
    }
    
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    
    public String getMake () {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }  
    
    public String getModel () {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getNoOfSeats () {
        return noOfSeats ;
    }
    
    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
    
    public String getEngineSize () {
        return engineSize ;
    }
    
    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }
    
    public String getDateBusBought () {
        return dateBusBought ;
    }
    
    public void setDateBusBought(String dateBusBought) {
        this.dateBusBought = dateBusBought;
    }
    
    public String getNextService () {
        return nextService ;
    }
    
    public void setNextService(String nextService) {
        this.nextService = nextService;
    }
}
        
        
    
        
 
