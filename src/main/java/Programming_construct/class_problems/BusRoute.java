import java.util.Arrays;

public class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    private static final int DEFAULT_PRIORITY = 1;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, DEFAULT_PRIORITY);
    }

    public String getRouteCode() {
        return this.routeCode;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public int getPriority() {
        return this.priority;
    }

    public int compareTo(BusRoute other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }

        int codeIgnoreCaseCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeIgnoreCaseCompare != 0) {
            return codeIgnoreCaseCompare;
        }

        int codeExactCompare = this.routeCode.compareTo(other.routeCode);
        if (codeExactCompare != 0) {
            return codeExactCompare;
        }

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) {
            return routes;
        }

        BusRoute[] sorted = routes.clone();
        int n = sorted.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    BusRoute temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        BusRoute[] inputRoutes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(inputRoutes);

        String[] codes = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            codes[i] = ranked[i].getRouteCode();
        }

        System.out.println(Arrays.toString(codes));
    }
}