package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entity.Location;
import entity.Recommendation;
import interfaceadapter.recommendation.RecommendationViewModel;

public class RecommendationView extends JFrame {
    private JButton viewMapButton;
    private RecommendationViewModel recommendationViewModel;

    public RecommendationView(RecommendationViewModel recommendationViewModel) {
        setTitle("Top 5 Recommendations");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JEditorPane resultPane = new JEditorPane();
        resultPane.setEditable(false);
        resultPane.setContentType("text/html");

        add(new JScrollPane(resultPane), BorderLayout.CENTER);

        StringBuilder stringB = new StringBuilder();
        stringB.append("<html><body style='text-align:left;'>");

        this.recommendationViewModel = recommendationViewModel;
        List<Recommendation> recommendations = recommendationViewModel.getState().getRecommendations();

        if (recommendations.isEmpty()) {
            stringB.append("<div style='font-size:16px;'><b>No locations found. Please try again later</b></div>");
        }
        else {
            int count = 0;
            for (Recommendation rec : recommendations) {
                List<Location> locList = rec.getLocations();
                for (Location loc : locList) {
                    stringB.append("<div>");
                    stringB.append("<span style='font-size:14px; font-weight:bold; font-family:SansSerif;'>")
                            .append(count + 1)
                            .append(". ")
                            .append(loc.getName())
                            .append("</span><br>");
                    stringB.append("<span style='font-size:12px; font-family:SansSerif;'>")
                            .append("Address: ")
                            .append(loc.getAddress())
                            .append("</span><br><br>");
                    stringB.append("</div>");
                    count++;
                }
            }
        }

        stringB.append("</body></html>");

        resultPane.setText(stringB.toString());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (recommendations.isEmpty()) {
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(event -> dispose());
            buttonPanel.add(closeButton);
        }
        else {
            viewMapButton = new JButton("View Map");
            viewMapButton.addActionListener(event -> {
                List<Location> locs = new ArrayList<>();
                for (Recommendation rec : recommendations) {
                    locs.addAll(rec.getLocations());
                }
                new MapView(locs);
            });
            buttonPanel.add(viewMapButton);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(event -> dispose());
            buttonPanel.add(closeButton);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public final JButton getViewMapButton() {
        return viewMapButton;
    }
}
