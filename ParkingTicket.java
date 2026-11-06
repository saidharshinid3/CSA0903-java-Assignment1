import java.time.LocalDateTime;

public class ParkingTicket {

    private String ticketNumber;
    private String vehicleNumber;
    private int floor;
    private int slot;
    private LocalDateTime entryTime;

    public ParkingTicket() {
        this("TKT001", "TN01AB1111", 0, 0,
                LocalDateTime.now());
    }

    public ParkingTicket(String ticketNumber,
                         String vehicleNumber,
                         int floor,
                         int slot) {
        this(ticketNumber, vehicleNumber, floor, slot,
                LocalDateTime.now());
    }

    public ParkingTicket(String ticketNumber,
                         String vehicleNumber,
                         int floor,
                         int slot,
                         LocalDateTime entryTime) {

        this.ticketNumber = ticketNumber;
        this.vehicleNumber = vehicleNumber;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void displayTicket() {
        System.out.println("Ticket: " + ticketNumber);
        System.out.println("Vehicle: " + vehicleNumber);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
        System.out.println("Entry: " + entryTime);
    }

    public static void main(String[] args) {
        ParkingTicket ticket = new ParkingTicket();
        ticket.displayTicket();
    }
}
