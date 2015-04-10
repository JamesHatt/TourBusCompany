package ca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BusTableGateway {
    
  private final Connection mConnection;
  
  private static final String TABLE_NAME = "buses";
  private static final String COLUMN_BUSESID = "busesID";
  private static final String COLUMN_REG_NO = "regNo";
  private static final String COLUMN_MAKE = "make";
  private static final String COLUMN_MODEL = "model";
  private static final String COLUMN_NO_OF_SEATS = "noOfSeats";
  private static final String COLUMN_ENGINE_SIZE = "engineSize";
  private static final String COLUMN_DATE_BUS_BOUGHT = "dateBusBought";
  private static final String COLUMN_NEXT_SERVICE = "nextService";
  private static final String COLUMN_GARAGE_ID = "garageID";
  
  public BusTableGateway(Connection connection) {
      mConnection = connection;
  }
  
  public int insertBus(String rn, String mk, String md, String nos, String es, String dbb, String ns, int gid)
      throws SQLException {
      String query;
      PreparedStatement stmt;
      int numRowsAffected;
      int id = -1;
      
      query = "INSERT INTO " + TABLE_NAME + " (" +
              COLUMN_REG_NO + ", " +
              COLUMN_MAKE + ", " +
              COLUMN_MODEL + ", " +
              COLUMN_NO_OF_SEATS + ", " +
              COLUMN_ENGINE_SIZE + ", " +
              COLUMN_DATE_BUS_BOUGHT + ", " +
              COLUMN_NEXT_SERVICE + ", " +
              COLUMN_GARAGE_ID +
              ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      
      stmt =mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, rn);
      stmt.setString(2, mk);
      stmt.setString(3, md);
      stmt.setString(4, nos);
      stmt.setString(5, es);
      stmt.setString(6, dbb);
      stmt.setString(7, ns);
      if(gid == -1) {
          stmt.setNull(8, java.sql.Types.INTEGER);
      }
      else { 
          stmt.setInt(8, gid);
      }
      
      numRowsAffected = stmt.executeUpdate();
      if (numRowsAffected == 1) {
          
          ResultSet keys = stmt.getGeneratedKeys();
          keys.next();
          
          id = keys.getInt(1);
      }
      
      return id;
  } 
  
  public boolean deleteBus(int id) throws SQLException {
      String query;
      PreparedStatement stmt;
      int numRowsAffected;
      
      query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_BUSESID + " = ?";
      
      stmt = mConnection.prepareStatement(query);
      stmt.setInt(1, id);
      
      numRowsAffected = stmt.executeUpdate();
      
      return (numRowsAffected == 1);
  }
   
  public List<Bus> getBuses() throws SQLException {
      String query;
      Statement stmt;
      ResultSet rs;
      List<Bus> buses;
      
      
      String regNo, make, model, noOfSeats, engineSize, dateBusBought, nextService;
      int busesID, garageId;
      
      Bus b;
      
      query = "SELECT * FROM " + TABLE_NAME;
      stmt = this.mConnection.createStatement();
      rs = stmt.executeQuery(query);
      
      
      buses = new ArrayList<Bus>();
      while (rs.next()){
          busesID = rs.getInt(COLUMN_BUSESID);
          regNo = rs.getString(COLUMN_REG_NO);
          make = rs.getString(COLUMN_MAKE);
          model = rs.getString(COLUMN_MODEL);
          noOfSeats = rs.getString(COLUMN_NO_OF_SEATS);
          engineSize= rs.getString(COLUMN_ENGINE_SIZE);
          dateBusBought = rs.getString(COLUMN_DATE_BUS_BOUGHT);
          nextService = rs.getString(COLUMN_NEXT_SERVICE);
          garageId = rs.getInt(COLUMN_GARAGE_ID);
          if (rs.wasNull()) {
              garageId = -1;
          }
          
          b = new Bus(busesID, regNo, make, model, noOfSeats, engineSize, dateBusBought, nextService, garageId);
          buses.add(b);
      }
      
      return buses;    
  }
  
    boolean updateBus(Bus b) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_REG_NO          + " = ?, " +
                COLUMN_MAKE            + " = ?, " +
                COLUMN_MODEL           + " = ?, " +
                COLUMN_NO_OF_SEATS     + " = ?, " +
                COLUMN_ENGINE_SIZE     + " = ?, " +
                COLUMN_DATE_BUS_BOUGHT + " = ?, " +
                COLUMN_NEXT_SERVICE    + " = ?, " +
                COLUMN_GARAGE_ID       + " = ? " +
                " WHERE " + COLUMN_BUSESID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, b.getRegNo());
        stmt.setString(2, b.getMake());
        stmt.setString(3, b.getModel());
        stmt.setString(4, b.getNoOfSeats());
        stmt.setString(5, b.getEngineSize());
        stmt.setString(6, b.getDateBusBought());
        stmt.setString(7, b.getNextService());
        int gid = b.getGarageID();
        if(gid == -1) {
            stmt.setNull(8, java.sql.Types.INTEGER);
        }
        else { 
            stmt.setInt(8, gid);
        }
        stmt.setInt(9, b.getbusesID());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1); 
    }
  
}
