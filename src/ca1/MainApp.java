package ca1;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static String line1;
    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        int opt;
        
        do{
            System.out.println("1. Create new Bus");
            System.out.println("2. Delete existing Bus");
            System.out.println("3. Edit existing Buses");
            System.out.println("4. View all Buses");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

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
            }
        }
        while (opt != 5);
    }
    
    private static void createBus(Scanner keyb, Model mdl) {
        Bus b = readBus(keyb);
         if (mdl.addBus(b)) {
             System.out.println("Bus added to database. ");
         }
         else {
             System.out.println("Bus not added to the database. ");
         }
         System.out.println();  
    }
    
        private static void deleteBus(Scanner keyboard, Model model) {
        System.out.print("Enter the Reg Number of the bus to delete: ");
        int busesID = Integer.parseInt(keyboard.nextLine());
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

    private static void editBus(Scanner kb, Model m) {
        System.out.print("Enter the bus id to edit: ");
        int busesID = Integer.parseInt(kb.nextLine());
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
            System.out.printf("%5s %20s %20s %20s %20s %20s %20s %20s %20s\n", "busesID", "RegNo", "Make", "Model", "noOfSeats", "EngineSize", "DateBusBought", "NextService", "Garage");
            for (Bus pr : buses) {
               System.out.printf("%5d %22s %20s %20s %20s %20s %20s %20s %20d\n",
                       pr.getbusesID(),                                
                       pr.getRegNo(),
                       pr.getMake(),
                       pr.getModel(),
                       pr.getNoOfSeats(),
                       pr.getEngineSize(),
                       pr.getDateBusBought(),
                       pr.getNextService(),
                       pr.getgarageID());
            }
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
        line1 = getString(keyb, "Enter garage id (enter -1 for no garage): ");
        garageID = Integer.parseInt(line1);
        
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
        String line;
        
        regNo = getString(keyb, "Enter Registration Number [" + b.getRegNo() + "]: ");
        make = getString(keyb, "Enter Make [" + b.getMake() + "]: ");
        model = getString(keyb, "Enter model [" + b.getModel() + "]: ");
        noOfSeats = getString(keyb, "Enter Number of Seats [" + b.getNoOfSeats() + "]: ");
        engineSize = getString(keyb, "Enter Engine Size [" + b.getEngineSize() + "]: ");
        dateBusBought = getString(keyb, "Enter the date the bus was bought [" + b.getDateBusBought() + "]: ");
        nextService = getString(keyb, "Enter the next bus service [" + b.getNextService() + "]: ");
        line1 = getString (keyb, "Enter garage id (enter -1 for no garage)[" + b.getgarageID() + "]: ");
        
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
        if (line1.length() != 0) {
            garageID = Integer.parseInt(line1);
            b.setgarageID(garageID);
        }  
    }
}
