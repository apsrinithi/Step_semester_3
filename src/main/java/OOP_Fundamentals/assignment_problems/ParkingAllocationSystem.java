import java.util.Scanner;

class ParkingLot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingLot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public boolean allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }
        return false;
    }

    public static ParkingLot findAvailableSlot(ParkingLot[] slots) {
        if (slots == null) {
            return null;
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }
        return null;
    }

    public static void safeAllot(ParkingLot[] slots, String vehicleNo) {
        ParkingLot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
            System.out.println(vehicleNo + " allotted to slot " + slot.slotNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }
}

public class ParkingAllocationSystem {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of parking slots: ");
        int n = sc.nextInt();
        ParkingLot[] slots = new ParkingLot[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter slotNo, capacity, and occupiedCount for slot " + (i + 1) + ": ");
            String slotNo = sc.next();
            int capacity = sc.nextInt();
            int occupiedCount = sc.nextInt();
            slots[i] = new ParkingLot(slotNo, capacity, occupiedCount);
        }

        System.out.print("Enter vehicle number for allotment: ");
        String vehicleNo = sc.next();

        System.out.println();
        ParkingLot.safeAllot(slots, vehicleNo);

        sc.close();
    }
}