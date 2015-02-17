package ca1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    
    private static Model instance = null;
    
    public static Model getInstance() {
        if (instance == null){
            instance = new Model();
        }
        return instance;
    }
    
    List<Bus> buses;
    BusTableGateway gateway;
    
    
    private Model() {
        
        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new BusTableGateway(conn);
            
            this.buses = this.gateway.getBuses();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public boolean addBus(Bus b) {
        boolean result = false;
        try {
            int id = this.gateway.insertBus(b.getRegNo(), b.getMake(), b.getModel(), b.getNoOfSeats(), b.getEngineSize(), b.getDateBusBought(), b.getNextService());
            if (id != -1) {
                b.setbusesID(id);
                this.buses.add(b);    
                result = true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean removeBus(Bus b) {
        boolean removed = false;
        
        try {
            removed = this.gateway.deleteBus(b.getbusesID());
            if (removed) {
                removed = this.buses.remove(b);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return removed;
    }
    
    public List<Bus> getBuses() {
        return this.buses;
    }

    Bus findBusByBusesID(int busesID) {
        Bus b = null;
        int i = 0;
        boolean found = false;
        while (i < this.buses.size() && !found) {
            b = this.buses.get(i);
            if (b.getbusesID() == busesID){
                found = true;
            }
            else {
                i++;
            }
        }
        if (!found) {
            b = null;
        }
        return b;
    }

    boolean updateBus(Bus b) {
        boolean updated = false;
        
        try {
            updated = this.gateway.updateBus(b);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updated;
    } 
}

