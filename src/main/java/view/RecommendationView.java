package view;

import entity.Location;
import entity.Recommendation;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class RecommendationView extends JPanel implements PropertyChangeListener, ActionListener {
    public JButton viewMapButton;
    private RecommendationController recommendationController;
    private RecommendationViewModel recommendationViewModel;

    public RecommendationView(RecommendationViewModel recommendationViewModel) {
        this.recommendationViewModel = recommendationViewModel;
        this.recommendationViewModel.addPropertyChangeListener(this);
        List<Recommendation> recommendations = recommendationViewModel.getState().getRecommendations();

        final JLabel title = new JLabel("Top 5 Recommendations");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        setTitle("Top 5 Recommendations");
        setSize(700,500);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JEditorPane resultPane = new JEditorPane();
        resultPane.setEditable(false);
        resultPane.setContentType("text/html");

        add(new JScrollPane(resultPane), BorderLayout.CENTER);

        StringBuilder stringB = new StringBuilder();
        stringB.append("<html><body style='text-align:left;'>");

        if (recommendations.isEmpty()) {
            stringB.append("<div style='font-size:16px;'><b>No locations found. Please try again later</b></div>");
        } else {
            int count = 0;
            for (Recommendation rec : recommendations) {
                List<Location> locList = rec.getLocations();
                for (Location loc : locList) {
//                    if (count >= 5) break;
                    stringB.append("<div>");
                    stringB.append("<span style='font-size:14px; font-weight:bold; font-family:SansSerif;'>")
                            .append((count + 1))
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
//                if (count >= 5) break;
            }
        }

        stringB.append("</body></html>");

        resultPane.setText(stringB.toString());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        viewMapButton = new JButton("View Map");
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
//        closeButton.addActionListener(e -> dispose());
        closeButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(viewMapButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        removeAll();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Recommended Locations:"));
        List<Recommendation> listRecommendation = recommendationViewModel.getState().getRecommendations();
        for (Recommendation recommendation : listRecommendation) {
            add(new JLabel(recommendation.toString()));
        }
    }

    public void setRecommendationController(RecommendationController recommendationController) {
    }
}