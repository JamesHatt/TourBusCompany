package ca1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    
    private static Model instance = null;
    
    public static Model getInstance() throws DataAccessException {
        if (instance == null){
            instance = new Model();
        }
        return instance;
    }
    
    List<Bus> buses;
    List<Garage> garages;
    BusTableGateway busGateway;
    GarageTableGateway garageGateway;
    
    
    private Model() throws DataAccessException {       
        try {
            Connection conn = DBConnection.getInstance();
            this.busGateway = new BusTableGateway(conn);
            this.garageGateway = new GarageTableGateway(conn);
            
            this.buses = this.busGateway.getBuses();
            this.garages = this.garageGateway.getGarages();
        } 
        catch (ClassNotFoundException ex) {
            throw new DataAccessException("Exception initialising Model object: " + ex.getMessage());                   
        } 
        catch (SQLException ex) {
            throw new DataAccessException("Exception initialising Model object: " + ex.getMessage());
        }    
    }
    
    public boolean addBus(Bus b) throws DataAccessException {
        boolean result = false;
        try {
            int id = this.busGateway.insertBus(
                    b.getRegNo(), b.getMake(), b.getModel(),
                    b.getNoOfSeats(), b.getEngineSize(),
                    b.getDateBusBought(), b.getNextService(), b.getGarageID());
            if (id != -1) {
                b.setbusesID(id);
                this.buses.add(b);    
                result = true;
            }
        }
        catch (SQLException ex) {
            throw new DataAccessException("Exception adding bus: " + ex.getMessage());
        }
        return result;
    }
    
    public boolean removeBus(Bus b) throws DataAccessException {
        boolean removed = false;
        
        try {
            removed = this.busGateway.deleteBus(b.getbusesID());
            if (removed) {
                removed = this.buses.remove(b);
            }
        }
        catch (SQLException ex) {
           throw new DataAccessException("Exception removing a bus: " + ex.getMessage());
        }
        
        return removed;
    }
    
    public List<Bus> getBuses() {
        return this.buses;
    }
    
    public List<Bus> getBusesByGarageID(int garageID) {
        List<Bus> list = new ArrayList<Bus>();
        for (Bus b : this.buses) {
            if (b.getGarageID() == garageID) {
                list.add(b);
            }
        }
        return list;
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

    boolean updateBus(Bus b) throws DataAccessException {
        boolean updated = false;
        
        try {
            updated = this.busGateway.updateBus(b);
        }
        catch (SQLException ex) {
           throw new DataAccessException("Exception updating bus: " + ex.getMessage());
        }
        
        return updated;
    } 
    
    public boolean addGarage(Garage g) throws DataAccessException {
    boolean result = false;
    try {
        int id = this.garageGateway.insertGarage(g.getName(), g.getAddress(),
                g.getPhoneNo(), g.getNameOfGarage(), g.getManager());
        if (id != -1) {
            g.setGarageID(id);
            this.garages.add(g);
            result = true;
        }
    }
    catch (SQLException ex) {
        throw new DataAccessException("Exception adding garage: " + ex.getMessage());
    }
    return result;
}
            
    public boolean removeGarage(Garage g) throws DataAccessException {
    boolean removed = false;

    try {
        removed = this.garageGateway.deleteGarage (g.getGarageID());
        if (removed) {
            removed = this.garages.remove(g);
        }
    }
    catch (SQLException ex) {
        throw new DataAccessException("Exception removing garage: " + ex.getMessage());
    }

    return removed;
}
                     
    public List<Garage> getGarages() {
        return this.garages;
    }          
            
        Garage findGarageById(int Id) {
        Garage g = null;
        int i = 0;
        boolean found = false;
        while (i < this.garages.size() && !found) {
            g = this.garages.get(i);
            if (g.getGarageID() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            g = null;
        }
        return g;
    }
    
    boolean updateGarage(Garage g) throws DataAccessException {
        boolean updated = false;

        try {
            updated = this.garageGateway.updateGarage(g);
        }
        catch (SQLException ex) {
            throw new DataAccessException("Exception updating garage: " + ex.getMessage());
        }

        return updated;
    }
            
}

