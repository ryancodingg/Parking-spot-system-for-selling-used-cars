/**
 * Project Description:  A company for selling used cars has a car park site. A small system is required that will help
manage used cars at a parking site for the company. You are to develop a system that should
has following four classes:
• Application class
• CarPark class
• ParkingSlot class
• Car class
 * @author Long Vu Nguyen
 * @id 104978318
 * @email 104978318
 * @version     
 * @date 2024-07-11
 */   

 import java.util.Scanner;

 public class Application {
     public static void main(String[] args) {
         int option = 0;
         Scanner sc = new Scanner(System.in);
         CarPark swinPark = new CarPark("Swinburne University Car Parking");
 
         do {
             System.out.println("\n\t... Welcome to " + swinPark.getTitle() + " ...");
             System.out.println("-----------------------------------------------");
 
             System.out.println("1. Add a parking slot");
             System.out.println("2. Delete a parking slot");
             System.out.println("3. List all the parking slots");
             System.out.println("4. Park a car");
             System.out.println("5. Find a car by rego");
             System.out.println("6. Remove a car by rego");
             System.out.println("7. Find cars by make");
             System.out.println("8. Exit");
             System.out.println("Please select the option from (1-8):");
 
             option = sc.nextInt();
 
             switch (option) {
                 case 1:
                     System.out.println("Please enter the parking slot ID, e.g., D001");
                     String slotID = sc.next();
                     if (!swinPark.isSlotIDUnique(slotID)) {
                         System.out.println("A slot with this ID already exists.");
                     } else {
                         try {
                             swinPark.addSlot(slotID);
                             System.out.println("Parking slot is added successfully");
                         } catch (IllegalArgumentException e) {
                             System.out.println(e.getMessage());
                         }
                     }
                     break;
 
                 case 2:
                     System.out.println("Please enter the ID of the slot to be deleted:");
                     String idToDel = sc.next();
                     if (swinPark.isSlotOccupied(idToDel)) {
                         System.out.println("Slot is occupied. Please remove the car first.");
                     } else {
                         swinPark.removeSlot(idToDel);
                     }
                     break;
 
                 case 3:
                     swinPark.displayAll();
                     break;
 
                 case 4:
                     System.out.println("Enter the ID of the parking slot:");
                     String idToPark = sc.next();
                     System.out.println("Enter the car registration number:");
                     String regNo = sc.next();
 
                     if (!Car.isValidRego(regNo)) {
                         System.out.println("Invalid rego. Must be alphanumeric and have a proper format.");
                     } else if (swinPark.isCarParked(regNo)) {
                         System.out.println("This car is already parked in another slot.");
                     } else {
                         swinPark.parkCar(idToPark, sc, regNo);
                     }
                     break;
 
                 case 5:
                     System.out.println("Please enter the rego of the car to be found, e.g., T1234");
                     String rego = sc.next();
                     ParkingSlot slot = swinPark.findByRego(rego);
                     if (slot == null) {
                         System.out.println("Sorry! " + rego + " was not found in the car park!");
                     } else {
                         System.out.println("Car found at: " + slot);
                     }
                     break;
 
                 case 6:
                     System.out.println("Please enter the rego of the car to be removed, e.g., T1234");
                     String regoToDel = sc.next();
                     System.out.println("Are you sure you want to remove the car from slot? Enter y/n:");
                     String confirm = sc.next();
                     if (confirm.equalsIgnoreCase("y")) {
                         swinPark.removeByRegoWithConfirmation(regoToDel);
                     } else {
                         System.out.println("Removal canceled. Returning to main menu.");
                     }
                     break;
 
                 case 7:
                     System.out.println("Please enter the make of the car to be searched, e.g., Audi");
                     String make = sc.next();
                     swinPark.searchByMake(make);
                     break;
 
                 case 8:
                     System.out.println("\t\t\tThank you for using the program!");
                     break;
 
                 default:
                     System.out.println("Invalid option. Please select an option from 1-8.");
                     break;
             }
         } while (option != 8);
     }
 }
 