public class TicketCounter {

    private static int counter = 1000;

    private int ticketNumber;

    public TicketCounter() {
        ticketNumber = ++counter;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public static void main(String[] args) {

        TicketCounter t1 = new TicketCounter();
        TicketCounter t2 = new TicketCounter();
        TicketCounter t3 = new TicketCounter();

        System.out.println(t1.getTicketNumber());
        System.out.println(t2.getTicketNumber());
        System.out.println(t3.getTicketNumber());
    }
}
