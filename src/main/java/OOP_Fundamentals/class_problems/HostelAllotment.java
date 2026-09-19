class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot(String studentName) {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
            System.out.println(studentName + " allotted to room " + room.roomNo);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }
}

public class HostelAllotment {
    public static void main(String[] args) {
        HostelRoom[] rooms1 = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        HostelRoom.safeAllot(rooms1, "Divya");

        HostelRoom[] rooms2 = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        HostelRoom.safeAllot(rooms2, "Divya");
    }
}