package ca1;

public class Garage {
       
    private int busesID;
    private String regNo;
    private String make;
    private String model;
    private String noOfSeats;
    private String engineSize;
    private String dateBusBought;
    private String nextService;
    private int garageID;

    public Garage(int id, String rn, String mk, String md, String nos, String es, String dbb, String ns, int gid) {
        this.busesID = bid;
        this.regNo = rn;
        this.make = mk;
        this.model = md;
        this.noOfSeats = nos;
        this.engineSize = es;
        this.dateBusBought = dbb;
        this.nextService = ns;
        this.garageID = gid;
    }

    public Garage(int bid, String rn, String mk, String md, String nos, String es, String dbb, String ns) {
        this(bid, rn, mk, md, nos, es, dbb, ns, -1);
    }

    public int getbId() {
        return bid;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
    
        public String getNoOfSeats() {
        return noOfSeats;
    }
        
    public String getEngineSize() {
        return engineSize;
    }
            
    public String getDateBusBought() {
        return dateBusBought;
    }
    
    public String getNextService() {
        return nextService;
    }
    
    public int getGarageID() {
        return garageID;
    }

    public void setbid(int id) {
        this.bid = bid;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }
        
    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public void setEngineSize(String engineSize) {
        this.make = make;
    }
    public void setDateBusBought(String dateBusBought) {
        this.dateBusBought = dateBusBought;
    }
    
    public void setNextService(String nextService) {
        this.nextService = nextService;
    }
    public void setGarageID(int garageID) {
        this.garageID = garageID;
    }
                
    }
