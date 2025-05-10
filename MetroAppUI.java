import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MetroAppUI {
    private RouteMap metroMap;

    // Getter for metroMap
    public RouteMap getMetroMap() {
        return metroMap;
    }

    // Constructor
    public MetroAppUI(RouteMap metroMap) {
        this.metroMap = metroMap;

        // Create JFrame
        JFrame frame = new JFrame("Metro Route Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout(10, 10));

        // Create input panel with GridLayout
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel sourceLabel = new JLabel("Select Source Station:");
        JComboBox<Station> sourceDropdown = new JComboBox<>();
        JLabel destLabel = new JLabel("Select Destination Station:");
        JComboBox<Station> destDropdown = new JComboBox<>();
        JButton findPathButton = new JButton("Find Shortest Path");

        // Populate dropdowns with stations
        for (Station s : metroMap.getStations()) {
            sourceDropdown.addItem(s);
            destDropdown.addItem(s);
        }

        // Add components to input panel
        inputPanel.add(sourceLabel);
        inputPanel.add(sourceDropdown);
        inputPanel.add(destLabel);
        inputPanel.add(destDropdown);
        inputPanel.add(findPathButton);

        // Create output panel with JTextArea for results
        JTextArea resultArea = new JTextArea(5, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add input panel and output panel to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listener for find path button
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get selected stations
                Station source = (Station) sourceDropdown.getSelectedItem();
                Station destination = (Station) destDropdown.getSelectedItem();

                // Error handling: Check if stations are selected
                if (source == null || destination == null) {
                    JOptionPane.showMessageDialog(frame, "Please select both source and destination stations.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if either station is not selected
                }

                // Error handling: Check if source and destination are the same
                if (source.equals(destination)) {
                    JOptionPane.showMessageDialog(frame, "Source and destination cannot be the same.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if stations are the same
                }

                // Find shortest path
                List<Station> shortestPath = metroMap.getShortestPath(source, destination);

                // Error handling: Check if path is found
                if (shortestPath.isEmpty()) {
                    resultArea.setText("No path found between " + source.getName() + " and " + destination.getName());
                } else {
                    StringBuilder path = new StringBuilder("Shortest Path: \n");
                    for (Station s : shortestPath) {
                        path.append(s.getName()).append(" -> ");
                    }
                    path.setLength(path.length() - 4); // Remove the last " -> "
                    resultArea.setText(path.toString());
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // Main method to launch the UI
    public static void main(String[] args) {
        RouteMap metroMap = new RouteMap();
        
        // Add stations
        Station s1 = new Station("Rajiv Chowk");
        Station s2 = new Station("Central Secretariat");
        Station s3 = new Station("Patel Chowk");
        metroMap.addStation(s1);
        metroMap.addStation(s2);
        metroMap.addStation(s3);

        // Connect stations
        metroMap.connectStations(s1, s2);
        metroMap.connectStations(s2, s3);

        // Launch the UI
        new MetroAppUI(metroMap);
    }
}

