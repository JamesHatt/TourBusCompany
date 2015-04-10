package ca1;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static String line1;
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model;
        int opt = 11;
        
        do{
            try{
                model = Model.getInstance();
                System.out.println(" 1. Create new Bus");
                System.out.println(" 2. Delete existing Bus");
                System.out.println(" 3. Edit existing Buses");
                System.out.println(" 4. View all buses");
                System.out.println(" 5. View single buses");
                System.out.println();
                System.out.println(" 6. Create new Garage");
                System.out.println(" 7. Delete existing Garage");
                System.out.println(" 8. Edit existing Garages");
                System.out.println(" 9. View all Garages");
                System.out.println(" 10. View single Garages");
                System.out.println();
                System.out.println(" 11. Exit");

                System.out.println();

                opt = getInt(keyboard, "Enter option: ",  11);

                System.out.println("You chose option " + opt);
                switch (opt) {

                    case 1: {
                        System.out.println("Creating Bus"); 
                        createBus(keyboard, model);
                        break;
                    }
                    case 2: {
                        System.out.println("Deleting Bus");  
                        deleteBus(keyboard, model);
                        break;
                        }
                    case 3: {
                        System.out.println("Editing Buses");
                        editBus(keyboard, model);
                        break;
                    }
                    case 4: {
                        System.out.println("Viewing Buses");
                        viewBuses(model);
                        break;
                    } 
                    case 5: {
                        System.out.println("Viewing single Bus");
                        viewBus(keyboard, model);
                        break;
                    } 
                    case 6: {
                        System.out.println("Creating garage"); 
                        createGarage(keyboard, model);
                        break;
                    }
                    case 7: {
                        System.out.println("Deleting garage");  
                        deleteGarage(keyboard, model);
                        break;
                        }
                    case 8: {
                        System.out.println("Editing garages");
                        editGarage(keyboard, model);
                        break;
                    }
                    case 9: {
                        System.out.println("Viewing garages");
                        viewGarages(model);
                        break;
                    }
                    case 10: {
                        System.out.println("Viewing garages");
                        viewGarage(keyboard, model);
                        break;
                    }
                }
            }
            catch (DataAccessException e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        while (opt != 11);
    }
        
    
    private static void createBus(Scanner keyb, Model mdl) throws DataAccessException {
      Bus b = readBus(keyb);  
            if (mdl.addBus(b)) {
                System.out.println("Bus added to database. ");
            }
            else {
                System.out.println("Bus not added to the database. ");
            }
            System.out.println();  
       }
   
    
    private static void deleteBus(Scanner keyboard, Model model) throws DataAccessException {
        int busesID = getInt(keyboard, "Enter the ID of the bus to delete:", -1);
    
        Bus b;

        b = model.findBusByBusesID(busesID);
        if (b != null) {
            if (model.removeBus(b)) {
                System.out.println("Bus deleted");
            }
            else {
                System.out.println("Bus not deleted ");
            }
        }
        else {
            System.out.println("Bus not found ");                   
        }
    }  

    private static void editBus(Scanner kb, Model m) throws DataAccessException {     
    int busesID = getInt(kb, "Enter the ID of the bus to edit:", -1);
        Bus b;
        
        b = m.findBusByBusesID(busesID);
        if (b != null) {
            editBusDetails(kb, b);
        if (m.updateBus(b)) {
            System.out.println("Bus updated");
        }
        else {
            System.out.println("Bus not updated");
        }
    }
    else {
        System.out.println("Bus not found");
    }
}

    private static void viewBuses(Model mdl) {
       List<Bus> buses = mdl.getBuses();
       System.out.println();
       if (buses.isEmpty()) {
            System.out.println("There are no buses in the database. ");
       }
       else { 
         displayBuses(buses, mdl);
    }
    System.out.println();
    }
    
    private static void displayBuses(List<Bus> buses, Model mdl) {
       System.out.printf("%5s %20s %20s %20s %20s %20s %20s %20s %20s\n",
               "busesID", "RegNo", "Make", "Model", "noOfSeats", 
               "EngineSize", "DateBusBought", "NextService", "Garage");
        for (Bus bs : buses) {
            Garage g = mdl.findGarageById(bs.getGarageID());
           System.out.printf("%5d %22s %20s %20s %20s %20s %20s %20s %20d\n",
                bs.getbusesID(),                                
                bs.getRegNo(),
                bs.getMake(),
                bs.getModel(),
                bs.getNoOfSeats(),
                bs.getEngineSize(),
                bs.getDateBusBought(),
                bs.getNextService(),
                (g != null) ? g.getNameOfGarage() : "");
        }
    }
    
        private static void viewBus(Scanner keyboard, Model model) throws DataAccessException {
        int busesID = getInt(keyboard, "Enter the ID of the bus to view:", -1);
    
        Bus b;

        b = model.findBusByBusesID(busesID);
        System.out.println();
        if (b != null) {
            Garage g = model.findGarageById(b.getGarageID());
            System.out.println("regNo         : " + b.getRegNo());
            System.out.println("make          : " + b.getMake());
            System.out.println("model         : " + b.getModel());
            System.out.println("regNo         : " + b.getNoOfSeats());
            System.out.println("noOfSeats     : " + b.getEngineSize());
            System.out.println("engineSize    : " + b.getDateBusBought());
            System.out.println("dateBusBought : " + b.getNextService());
            System.out.println("Garage        : " + ((g != null) ? g.getNameOfGarage() : ""));
        }
        else {
            System.out.println("Bus not found ");                   
        }
        System.out.println();
    }
    
    private static Bus readBus(Scanner keyb) {
        String regNo, make, model, noOfSeats, engineSize, dateBusBought, nextService;
        int garageID;
        String line1;
        
        regNo = getString(keyb, "Enter Registration number please: ");
        make = getString(keyb, "Enter the make please: ");
        model = getString(keyb, "Enter the model please: ");
        noOfSeats = getString(keyb, "Enter the number of seats please: ");
        engineSize = getString(keyb, "Enter the Engine Size please: ");
        dateBusBought = getString(keyb, "Enter the date the bus was bought please: ");
        nextService = getString(keyb, "Enter the next service date please: ");
        garageID = getInt(keyb, "Enter garage id (enter -1 for no garage): ", -1);

        Bus b = 
                new Bus(regNo, make, model, noOfSeats,
                        engineSize, dateBusBought, nextService, garageID);
        
        return b;
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();  
    }

    private static void editBusDetails(Scanner keyb, Bus b) {
        String regNo, make, model, noOfSeats, engineSize, dateBusBought, nextService;
        int garageID;
        String line1;
        
        regNo = getString(keyb, "Enter Registration Number [" + b.getRegNo() + "]: ");
        make = getString(keyb, "Enter Make [" + b.getMake() + "]: ");
        model = getString(keyb, "Enter model [" + b.getModel() + "]: ");
        noOfSeats = getString(keyb, "Enter Number of Seats [" + b.getNoOfSeats() + "]: ");
        engineSize = getString(keyb, "Enter Engine Size [" + b.getEngineSize() + "]: ");
        dateBusBought = getString(keyb, "Enter the date the bus was bought [" + b.getDateBusBought() + "]: ");
        nextService = getString(keyb, "Enter the next bus service [" + b.getNextService() + "]: ");
        garageID = getInt(keyb, "Enter garage id (enter -1 for no garage)[" + b.getGarageID() + "]: ", -1);
        
        if (regNo.length() != 0) {
            b.setRegNo(regNo);
        }
        if (make.length() != 0) {
            b.setMake(make);
        }
        if (model.length() != 0) {
            b.setModel(model);
        }
        if (noOfSeats.length() != 0) {
            b.setNoOfSeats(noOfSeats);
        }
        if (engineSize.length() != 0) {
            b.setEngineSize(engineSize);
        }
        if (dateBusBought.length() != 0) {
            b.setDateBusBought(dateBusBought);
        }
        if (nextService.length() != 0) {
            b.setNextService(nextService);
        }   
        if (garageID != b.getGarageID()) {
            b.setGarageID(garageID);
        }
        
    }
    
    private static void createGarage(Scanner keyb, Model mdl) throws DataAccessException {
    Garage g = readGarage(keyb);
    if (mdl.addGarage(g)) {
        System.out.println("Garage added to database.");
    }
    else {
        System.out.println("Garage not added to database.");
    }
    System.out.println();
}

    private static void deleteGarage(Scanner keyboard, Model model) throws DataAccessException {
        int garageID = getInt(keyboard, "Enter the id of the garage to delete:", -1);
        Garage g;

        g = model.findGarageById(garageID);
        if (g != null) {
            if (model.removeGarage(g)) {
                System.out.println("Garage deleted");
            }
            else {
                System.out.println("Garage not deleted");
            }
        }
        else {
            System.out.println("Garage not found");
        }
    }
    
      private static void editGarage(Scanner kb, Model m) throws DataAccessException {
        int garageID = getInt(kb, "Enter the id of the garage to edit:", -1);
        Garage g;

        g = m.findGarageById(garageID);
        if (g != null) {
            editGarageDetails(kb, g);
            if (m.updateGarage(g)) {
                System.out.println("Garage updated");
            }
            else {
                System.out.println("Garage not updated");
            }
        }
        else {
            System.out.println("Garage not found");
        }
    }
      
    private static void viewGarages(Model mdl) {
        List<Garage> garages = mdl.getGarages();
        System.out.println();
        if (garages.isEmpty()) {
            System.out.println("There are no garages in the database.");
        }
        else {
            System.out.printf("%5s %20s %20s %15s %10s %10s\n",
                    "Id", "Name", "Address", "PhoneNo", "NameOfGarage", "Manager");
            for (Garage g : garages) {
                System.out.printf("%5d %20s %20s %15s %10s %10s\n",
                        g.getGarageID(),
                        g.getName(),
                        g.getAddress(),
                        g.getPhoneNo(),
                        g.getNameOfGarage(),
                        g.getManager());
            }
        }
        System.out.println();
    }
    
    private static void viewGarage(Scanner keyboard, Model model) {
        int garageID = getInt(keyboard, "Enter the id of the garage to view:", -1);
        Garage g;

        g = model.findGarageById(garageID);
        System.out.println();
        if (g != null) { 
            System.out.println("name    : " + g.getName());
            System.out.println("address : " + g.getAddress());
            System.out.println("phoneNo : " + g.getPhoneNo());
            System.out.println("regNo   : " + g.getNameOfGarage());
            System.out.println("manager : " + g.getManager());           
        
            List<Bus> busList = model.getBusesByGarageID(g.getGarageID());
            System.out.println();
            if (busList.isEmpty()){
               System.out.println("This garage stores no buses"); 
            }
            else {
                System.out.println("This garage stores the folowing buses:");
                System.out.println();
                displayBuses(busList, model);
            }
            System.out.println();
        }
        else {
            System.out.println("Garage not found");
        }
        System.out.println();
    }
    
    private static Garage readGarage(Scanner keyb) {
        String name, address, phoneNo, nameOfGarage, manager;
        int garageID;
        String line1;

        name = getString(keyb, "Enter name: ");
        address = getString(keyb, "Enter address: ");
        phoneNo = getString(keyb, "Enter phoneNo: ");
        nameOfGarage = getString(keyb, "Enter nameOfGarage: ");
        manager = getString(keyb, "Enter manager: ");
        line1 = getString(keyb, "Enter garage id (enter -1 for no garage): ");
        garageID = Integer.parseInt(line1);

        Garage g = new Garage(garageID, name, address, phoneNo, nameOfGarage, manager);

        return g;
    }
        
    private static void editGarageDetails(Scanner keyb, Garage g) {
        String name, address, phoneNo, nameOfGarage, manager;
        int garageID;
        String line1;

        name = getString(keyb, "Enter name [" +  g.getName() + "]: ");
        address = getString(keyb, "Enter address [" +  g.getAddress() + "]: ");
        phoneNo = getString(keyb, "Enter phoneNo [" +  g.getPhoneNo() + "]: ");
        nameOfGarage = getString(keyb, "Enter nameOfGarage [" +  g.getNameOfGarage() + "]: ");
        manager = getString(keyb, "Enter manager [" +  g.getManager() + "]: ");
        line1 = getString (keyb, "Enter garage id (enter -1 for no garage)[" + g.getGarageID() + "]: ");

        if (name.length() != 0) {
            g.setName(name);
        }
        if (address.length() != 0) {
            g.setAddress(address);
        }
        if (phoneNo.length() != 0) {
            g.setPhoneNo(phoneNo);
        }
        if (nameOfGarage.length() != 0) {
            g.setNameOfGarage(nameOfGarage);
        }
        if (manager.length() != 0) {
            g.setManager(manager);
        }
        if (line1.length() != 0) {
            garageID = Integer.parseInt(line1);
            g.setGarageID(garageID);
        }
    }
        
        private static int getInt(Scanner keyb, String prompt, int defaultValue) {
            int opt = defaultValue;
            boolean finished = false;
            
            do {
                try {         
                    System.out.print(prompt);
                    String line = keyb.nextLine();
                    if (line.length() > -1) {
                        opt = Integer.parseInt(line);
                    }
                    finished = true;
                }
                catch (NumberFormatException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
            while (!finished);
            
            return opt;
        }
        
    } 


   

