public class Main {
    public static void main(String[] args) {
        // Create station objects
        Station s1 = new Station("Rajiv Chowk");
        Station s2 = new Station("Central Secretariat");
        Station s3 = new Station("Patel Chowk");

        // Create an instance of RouteMap
        RouteMap metroMap = new RouteMap();
        
        // Add stations to the metro map
        metroMap.addStation(s1);
        metroMap.addStation(s2);
        metroMap.addStation(s3);

        // Connect stations
        metroMap.connectStations(s1, s2);  // Rajiv Chowk <-> Central Secretariat
        metroMap.connectStations(s2, s3);  // Central Secretariat <-> Patel Chowk

        // Display the metro map with connections
        System.out.println("Metro Map:");
        metroMap.showMap();

        // Find and display the shortest path from Rajiv Chowk to Patel Chowk
        System.out.println("\nShortest path from Rajiv Chowk to Patel Chowk:");
        for (Station s : metroMap.getShortestPath(s1, s3)) {
            System.out.print(s + " -> ");
        }
        System.out.println("END");
        new MetroAppUI(metroMap); // <-- UI launch

    }
}

/*Explanation:
Station Objects: Three stations (Rajiv Chowk, Central Secretariat, Patel Chowk) are created.

RouteMap Object: A RouteMap instance is created to manage the stations and connections.

Adding Stations: The stations are added to the metro map.

Connecting Stations: connectStations() establishes bidirectional connections between stations.

Displaying the Map: showMap() prints the station connections.

Shortest Path: The getShortestPath() method is used to find the shortest route from Rajiv Chowk to Patel Chowk using BFS, and it prints the path.*/




