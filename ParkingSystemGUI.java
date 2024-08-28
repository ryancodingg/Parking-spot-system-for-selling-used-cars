import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingSystemGUI extends JFrame {

    private CarPark carPark;
    private JButton[] parkingSlots;
    private final int totalSlots = 10; // Assume 10 slots for this example

    public ParkingSystemGUI() {
        carPark = new CarPark("Swinburne University Car Parking");

        // Set up the main frame
        setTitle("Swinburne Car Park System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Swinburne University Car Park System", JLabel.CENTER);
        headerLabel.setForeground(Color.decode("#2F4156"));  // Set the text color to #2F4156
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Parking Slots Panel
        JPanel parkingSlotsPanel = new JPanel();
        parkingSlotsPanel.setLayout(new GridLayout(2, 5));  // 2 rows, 5 columns
        parkingSlots = new JButton[totalSlots];

        // Initialize parking slots with the vacant color and light blue border
        for (int i = 0; i < totalSlots; i++) {
            parkingSlots[i] = new JButton("Slot " + (i + 1) + " Vacant");
            parkingSlots[i].setBackground(Color.decode("#F5EFEB"));  
            parkingSlots[i].setForeground(Color.decode("#2F4156"));  
            parkingSlots[i].setOpaque(true);  
            parkingSlots[i].setBorder(new LineBorder(Color.decode("#C8C9E6"), 2));  
            parkingSlots[i].addActionListener(new SlotButtonListener(i));  
            parkingSlotsPanel.add(parkingSlots[i]);
        }


        add(parkingSlotsPanel, BorderLayout.CENTER);

        // Control Panel for buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2));  

        JButton showAllButton = new JButton("Show All Parking Spots");
        showAllButton.setForeground(Color.decode("#2F4156"));  
        showAllButton.setBackground(Color.decode("#F5EFEB"));  
        showAllButton.setOpaque(true);  
        showAllButton.setContentAreaFilled(true); 

        JButton parkCarButton = new JButton("Park Car");
        parkCarButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        parkCarButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        parkCarButton.setOpaque(true);  // Ensure background is respected
        parkCarButton.setContentAreaFilled(true);  // Force background to be filled

        JButton removeCarButton = new JButton("Remove Car");
        removeCarButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        removeCarButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        removeCarButton.setOpaque(true);  // Ensure background is respected
        removeCarButton.setContentAreaFilled(true);  // Force background to be filled

        JButton exitButton = new JButton("Exit Application");
        exitButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        exitButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        exitButton.setOpaque(true);  // Ensure background is respected
        exitButton.setContentAreaFilled(true);  // Force background to be filled

        JButton findCarButton = new JButton("Find Car");
        findCarButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        findCarButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        findCarButton.setOpaque(true);  // Ensure background is respected
        findCarButton.setContentAreaFilled(true);  // Force background to be filled

        JButton deleteSlotButton = new JButton("Delete Spot");
        deleteSlotButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        deleteSlotButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        deleteSlotButton.setOpaque(true);  // Ensure background is respected
        deleteSlotButton.setContentAreaFilled(true);  // Force background to be filled

        JButton addSlotButton = new JButton("Add Parking Spot");
        addSlotButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        addSlotButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        addSlotButton.setOpaque(true);  // Ensure background is respected
        addSlotButton.setContentAreaFilled(true);  // Force background to be filled

        JButton clearScreenButton = new JButton("Clear Screen");
        clearScreenButton.setForeground(Color.decode("#2F4156"));  // Set text color to navy
        clearScreenButton.setBackground(Color.decode("#F5EFEB"));  // Set background color to #F5EFEB
        clearScreenButton.setOpaque(true);  // Ensure background is respected
        clearScreenButton.setContentAreaFilled(true);  // Force background to be filled

        controlPanel.add(showAllButton);
        controlPanel.add(findCarButton);
        controlPanel.add(parkCarButton);
        controlPanel.add(deleteSlotButton);
        controlPanel.add(removeCarButton);
        controlPanel.add(addSlotButton);
        controlPanel.add(exitButton);
        controlPanel.add(clearScreenButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Add ActionListeners for buttons
        showAllButton.addActionListener(e -> showAllSlots());
        parkCarButton.addActionListener(e -> parkCar());
        removeCarButton.addActionListener(e -> removeCar());
        exitButton.addActionListener(e -> System.exit(0));
        findCarButton.addActionListener(e -> findCar());
        deleteSlotButton.addActionListener(e -> deleteSlot());
        addSlotButton.addActionListener(e -> addParkingSlot());
        clearScreenButton.addActionListener(e -> clearScreen());

        // Make frame visible
        setVisible(true);
    }

    // Show all parking slots
    private void showAllSlots() {
        StringBuilder allSlots = new StringBuilder("Parking Spots Status:\n");

        // Iterate over all parking slots in the CarPark
        for (int i = 0; i < parkingSlots.length; i++) {
            String buttonLabel = parkingSlots[i].getText();

            // Check if the parking slot is vacant or occupied
            if (buttonLabel.contains("Vacant")) {
                allSlots.append(buttonLabel).append(" - Available\n");
            } else if (buttonLabel.contains("Occupied")) {
                allSlots.append(buttonLabel).append(" - Not Available\n");
            }
        }

        // Show all slots in a message dialog
        JOptionPane.showMessageDialog(this, allSlots.toString(), "Parking Slots", JOptionPane.INFORMATION_MESSAGE);
    }

    // Park a car in a slot
    private void parkCar() {
        String slotID = JOptionPane.showInputDialog(this, "Enter Slot ID:");
        if (slotID == null || slotID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Slot ID!");
            return;
        }

        // Check if the slot exists in the CarPark
        ParkingSlot slot = carPark.findBySlotID(slotID);
        if (slot == null) {
            JOptionPane.showMessageDialog(this, "Slot does not exist!");
            return;
        }

        // Check if the slot is already occupied
        if (slot.getParkedCar() != null) {
            JOptionPane.showMessageDialog(this, "Slot is already occupied!");
            return;
        }

        // Input car details
        String regNo = JOptionPane.showInputDialog(this, "Enter Car Registration Number:");
        String make = JOptionPane.showInputDialog(this, "Enter Car Make:");
        String model = JOptionPane.showInputDialog(this, "Enter Car Model:");
        int year;
        try {
            year = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Car Year:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Year!");
            return;
        }

        // Create a new Car object and park the car in the slot
        Car car = new Car(regNo, make, model, year);
        slot.parkCar(car);
        JOptionPane.showMessageDialog(this, "Car parked successfully!");

        // Update the corresponding slot in the GUI when a car is parked
        for (JButton parkingSlotButton : parkingSlots) {
            if (parkingSlotButton.getText().contains(slotID)) {
                parkingSlotButton.setText(slotID + " Occupied by " + regNo);
                parkingSlotButton.setBackground(Color.decode("#2F4156"));  // Set background to #2F4156
                parkingSlotButton.setForeground(Color.decode("#F5EFEB"));  // Set text color to #F5EFEB
                parkingSlotButton.setOpaque(true);  // Ensure the background color is respected
                parkingSlotButton.setBorderPainted(false);  // Remove border painting
                break;
            }
        }
    }

    // Remove a car from a slot
    private void removeCar() {
        String regNo = JOptionPane.showInputDialog(this, "Enter Car Registration Number to remove:");
        if (regNo == null || regNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Registration Number!");
            return;
        }

        ParkingSlot slot = carPark.findByRego(regNo);
        if (slot == null) {
            JOptionPane.showMessageDialog(this, "Car not found!");
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the car?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            slot.removeCar();
            JOptionPane.showMessageDialog(this, "Car removed from slot: " + slot.getSlotID());

            // Update the corresponding slot in the GUI when a car is removed
            // When a car is removed, restore the background and light blue border
        for (JButton parkingSlotButton : parkingSlots) {
            if (parkingSlotButton.getText().contains(slot.getSlotID())) {
            parkingSlotButton.setText(slot.getSlotID() + " Vacant");
            parkingSlotButton.setBackground(Color.decode("#F5EFEB"));  // Set the background back to #F5EFEB
            parkingSlotButton.setForeground(Color.BLACK);  // Reset the text color to black
            parkingSlotButton.setOpaque(true);  // Ensure the background color is respected
            parkingSlotButton.setBorder(new LineBorder(Color.decode("#C8C9E6"), 2));  // Restore the light blue border #C8C9E6
            break;
            }
        }

            }
     }
    

    // Find a car by registration number
    private void findCar() {
        String regNo = JOptionPane.showInputDialog(this, "Enter Car Registration Number:");
        if (regNo == null || regNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Registration Number!");
            return;
        }

        ParkingSlot slot = carPark.findByRego(regNo);
        if (slot == null) {
            JOptionPane.showMessageDialog(this, "Car not found!");
        } else {
            JOptionPane.showMessageDialog(this, "Car found at slot: " + slot.getSlotID());
        }
    }

// Delete a parking slot
private void deleteSlot() {
    String slotID = JOptionPane.showInputDialog(this, "Enter Slot ID to delete:");
    if (slotID == null || slotID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Invalid Slot ID!");
        return;
    }

    try {
        carPark.removeSlot(slotID);
        JOptionPane.showMessageDialog(this, "Parking slot deleted successfully!");

        // Update the corresponding slot in the GUI when a slot is deleted
        for (int i = 0; i < parkingSlots.length; i++) {
            JButton parkingSlotButton = parkingSlots[i];
            if (parkingSlotButton.getText().contains(slotID)) {
                // Restore the original text of the button to "Slot X Vacant"
                parkingSlotButton.setText("Slot " + (i + 1) + " Vacant");
                parkingSlotButton.setBackground(Color.decode("#F5EFEB"));  // Set the background back to vacant
                parkingSlotButton.setForeground(Color.BLACK);  // Reset the text color to black
                parkingSlotButton.setOpaque(true);  // Ensure background color is respected
                parkingSlotButton.setBorder(new LineBorder(Color.decode("#C8C9E6"), 2));  // Restore light blue border color #C8C9E6
                parkingSlotButton.setBorderPainted(true);  // Ensure the border is painted
                break;
            }
        }

    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}



    // Add a parking slot
    private void addParkingSlot() {
        String slotID = JOptionPane.showInputDialog(this, "Enter Slot ID (e.g., D001):");
        if (slotID == null || slotID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Slot ID!");
            return;
        }

        // Check if the slotID already exists in the CarPark
        if (carPark.findBySlotID(slotID) != null) {
            JOptionPane.showMessageDialog(this, "Slot with this ID already exists!");
            return;
        }

        try {
            // Add the slot to the CarPark
            carPark.addSlot(slotID);
            JOptionPane.showMessageDialog(this, "Parking slot added successfully!");

            // Find the first vacant button that has not been assigned yet and update it
            for (int i = 0; i < parkingSlots.length; i++) {
                if (parkingSlots[i].getText().contains("Vacant") && parkingSlots[i].getText().startsWith("Slot ")) {
                    parkingSlots[i].setText(slotID + " Vacant");
                    parkingSlots[i].setBackground(Color.decode("#F5EFEB"));  // Set the background back to vacant
                    parkingSlots[i].setForeground(Color.BLACK);  // Reset the text color
                    parkingSlots[i].setBorder(new LineBorder(Color.decode("#567CBD"), 2));  // Restore border
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Clear the screen (reset all slots)
    private void clearScreen() { // Clear the screen (reset all slots)
    for (int i = 0; i < parkingSlots.length; i++) {
            parkingSlots[i].setText("Slot " + (i + 1) + " Vacant");
            parkingSlots[i].setBackground(Color.decode("#F5EFEB"));  // Set vacant color
            parkingSlots[i].setForeground(Color.BLACK);  // Reset the text color to black
            parkingSlots[i].setOpaque(true);  // Ensure background color is respected
            parkingSlots[i].setBorder(new LineBorder(Color.decode("#C8C9E6"), 2));  // Restore light blue border color #C8C9E6
    }

    }

    // Slot Button Listener Class
    private class SlotButtonListener implements ActionListener {
        private final int index;

        public SlotButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = parkingSlots[index];

            if (clickedButton.getText().contains("Vacant")) {
                // Treat this as adding a new slot
                String slotID = JOptionPane.showInputDialog(ParkingSystemGUI.this, "Enter Slot ID for Slot " + (index + 1));
                if (slotID == null || slotID.isEmpty()) {
                    JOptionPane.showMessageDialog(ParkingSystemGUI.this, "Invalid Slot ID!");
                    return;
                }

                // Check if the slotID already exists in the CarPark
                if (carPark.findBySlotID(slotID) != null) {
                    JOptionPane.showMessageDialog(ParkingSystemGUI.this, "Slot with this ID already exists!");
                    return;
                }

                try {
                    carPark.addSlot(slotID);
                    clickedButton.setText(slotID + " Vacant");
                    clickedButton.setBackground(Color.decode("#F5EFEB"));  // Set vacant color
                    clickedButton.setForeground(Color.BLACK);  // Reset the text color to black
                    clickedButton.setBorder(new LineBorder(Color.decode("#567CBD"), 2));  // Restore border
                    JOptionPane.showMessageDialog(ParkingSystemGUI.this, "Parking slot " + slotID + " added successfully.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ParkingSystemGUI.this, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(ParkingSystemGUI.this, "This slot is already occupied or assigned.");
            }
        }
    }

    public static void main(String[] args) {
        new ParkingSystemGUI();
    }
}