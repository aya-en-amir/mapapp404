package view;

import entity.Location;
import entity.Recommendation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecommendationView extends JFrame {

    public RecommendationView(List<Recommendation> recommendations) {
        setTitle("Top 5 Recommendations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        StringBuilder stringB = new StringBuilder();

        if (recommendations.isEmpty()) {
            stringB.append("No locations found.");
        } else {
            int count = 0;
            for (Recommendation rec : recommendations) {
                List<Location> locList = rec.getLocations();
                for (Location loc : locList) {
                    if (count >= 5) break;
                    stringB.append((count + 1)).append(". ").append(loc.getName()).append("\n")
                            .append("\tAddress: ").append(loc.getAddress()).append("\n\n");
                    count++;
                }
                if (count >= 5) break;
            }
        }

        resultArea.setText(stringB.toString());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton viewMapButton = new JButton("View Map");
        viewMapButton.addActionListener(e -> {
            if (!recommendations.isEmpty()) {
                List<Location> locs = new ArrayList<>();
                for (Recommendation rec : recommendations) {
                    locs.addAll(rec.getLocations());
                }
                new MapView(locs);
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        buttonPanel.add(viewMapButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}