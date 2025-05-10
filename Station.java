public class Station {
    private String name;

    // Constructor to initialize the station with a name
    public Station(String name) {
        this.name = name;
    }

    // Getter method for the station name
    public String getName() {
        return name;
    }

    // Override the toString() method to display the station name when printing
    @Override
    public String toString() {
        return name;
    }

    // Override equals() and hashCode() to ensure proper comparison in HashMap and HashSet
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Station station = (Station) obj;
        return name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
/* Explanation of the Code:
name: A private field to store the name of the station.

Constructor: The constructor takes the station name as an argument and initializes the name field.

getName(): A getter method to retrieve the name of the station.

toString(): This method is overridden to return the name of the station. This makes it easier to print the station when used in collections like System.out.print() or System.out.println().

equals() and hashCode(): These methods are overridden to ensure that Station objects are compared based on their name field, which is important when using Station objects as keys in a HashMap or adding them to a HashSet.

Why equals() and hashCode() are important:
In collections like HashMap and HashSet, Java relies on equals() and hashCode() to determine object equality. By default, Java compares objects by their reference, but in this case, we want stations with the same name to be considered equal.

Overriding these methods ensures that two Station objects with the same name are treated as equal when performing operations like adding/removing from a map or set.

Example Usage:
In the RouteMap class and Main.java, the Station class will be used to represent individual metro stations, and you'll see its name printed when calling toString().

Let me know if you need any further adjustments or explanations!






*/