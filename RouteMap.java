import java.util.*;

public class RouteMap {
    // A map to store station connections
    private Map<Station, List<Station>> stationMap;

    // Constructor to initialize the station map
    public RouteMap() {
        stationMap = new HashMap<>();
    }

    // Method to add a station to the map
    public void addStation(Station station) {
        if (!stationMap.containsKey(station)) {
            stationMap.put(station, new ArrayList<>());
        }
    }

    // Method to connect two stations (bidirectional)
    public void connectStations(Station s1, Station s2) {
        // Add the second station to the list of connections for the first station
        stationMap.get(s1).add(s2);
        // Add the first station to the list of connections for the second station
        stationMap.get(s2).add(s1);
    }

    // Method to display the entire metro map
    public void showMap() {
        for (Station station : stationMap.keySet()) {
            System.out.print(station.getName() + " is connected to: ");
            for (Station connectedStation : stationMap.get(station)) {
                System.out.print(connectedStation.getName() + " ");
            }
            System.out.println();
        }
    }

    // Method to find the shortest path from a starting station to an ending station using BFS
    public List<Station> getShortestPath(Station start, Station end) {
        // Queue for BFS traversal
        Queue<Station> queue = new LinkedList<>();
        // Map to track the previous station of each station for path reconstruction
        Map<Station, Station> prevStationMap = new HashMap<>();
        // Set to track visited stations
        Set<Station> visited = new HashSet<>();

        // Start BFS from the start station
        queue.offer(start);
        visited.add(start);

        // BFS loop
        while (!queue.isEmpty()) {
            Station current = queue.poll();

            // If we have reached the destination station, reconstruct the path
            if (current.equals(end)) {
                List<Station> path = new ArrayList<>();
                for (Station at = end; at != null; at = prevStationMap.get(at)) {
                    path.add(at);
                }
                Collections.reverse(path);
                return path;  // Return the path from start to end
            }

            

            // Explore all connected stations (neighbors)
            for (Station neighbor : stationMap.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    prevStationMap.put(neighbor, current);  // Track the previous station
                    queue.offer(neighbor);  // Add the neighbor to the queue
                }
            }
        }
        return new ArrayList<>();  // Return an empty list if no path found
    }
        // Method to return all stations
        public List<Station> getStations() {
            return new ArrayList<>(stationMap.keySet());
        }
    
}

/*Explanation of the Code:
stationMap: A HashMap that stores each Station as a key and a list of connected stations (neighbors) as values.

addStation(Station station): Adds a station to the map if it’s not already present.

connectStations(Station s1, Station s2): Creates bidirectional connections between two stations by adding each to the other’s list of connections.

showMap(): Prints out the entire metro map, showing each station and its connected stations.

getShortestPath(Station start, Station end): Implements the BFS algorithm to find the shortest path between two stations. It uses a queue to explore all reachable stations and tracks the previous station for each one to reconstruct the shortest path once the destination is reached.

 */



