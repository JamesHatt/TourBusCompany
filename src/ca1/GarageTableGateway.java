package ca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GarageTableGateway {

    private Connection mConnection;

    private static final String TABLE_NAME = "garages";
    private static final String COLUMN_GARAGEID = "garage ID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHONENO = "phoneNo";
    private static final String COLUMN_NAMEOFGARAGE = "nameOfGarages";
    private static final String COLUMN_MANAGER = "manager";

    public GarageTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertGarage(String n, String a, String pn, String nog, String m) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int id = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME         + ", " +
                COLUMN_ADDRESS      + ", " +
                COLUMN_PHONENO      + ", " +
                COLUMN_NAMEOFGARAGE + ", " +
                COLUMN_MANAGER      +
                ") VALUES (?, ?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, n);
        stmt.setString(2, a);
        stmt.setString(3, pn);
        stmt.setString(4, nog);
        stmt.setString(5, m);

        // execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
        }

        // return the id assigned to the row in the database
        return id;
    }

    public boolean deleteGarage(int id) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL DELETE statement with place holders for the id of the row to be remove from the database
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_GARAGEID + " = ?";

        // create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was deleted from the database
        return (numRowsAffected == 1);
    }

    public List<Garage> getGarages() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Garage> garages;         // the java.util.List containing the Garage objects created for each row
                                        // in the result of the query the id of a garage

        String name, address, phoneNo, nameOfGarage, manager;
        int id;
        Garage g;                   // a Garage object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Garage object, which is inserted into an initially
        // empty ArrayList
        garages = new ArrayList<Garage>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_GARAGEID);
            name = rs.getString(COLUMN_NAME);
            address = rs.getString(COLUMN_ADDRESS);
            address = rs.getString(COLUMN_PHONENO);
            nameOfGarage = rs.getString(COLUMN_NAMEOFGARAGE);
            manager = rs.getString(COLUMN_MANAGER);

            g = new Garage(id, name, address, phoneNo, nameOfGarage, manager);
            garages.add(g);
        }

        // return the list of Garage objects retrieved
        return garages;
    }

    boolean updateGarage(Garage g) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME         + " = ?, " +
                COLUMN_ADDRESS      + " = ?, " +
                COLUMN_PHONENO      + " = ?, " +
                COLUMN_NAMEOFGARAGE + " = ? " +
                COLUMN_MANAGER + " = ? " +
                " WHERE " + COLUMN_GARAGEID + " = ?";

        // create a PreparedStatement object to execute the query and insert the new values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, g.getName());
        stmt.setString(2, g.getAddress());
        stmt.setString(3, g.getPhoneNo());
        stmt.setString(4, g.getNameOfGarage());
        stmt.setString(5, g.getManager());

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was updated in the database
        return (numRowsAffected == 1);
    }

}