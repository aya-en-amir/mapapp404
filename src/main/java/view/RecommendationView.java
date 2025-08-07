package view;

import entity.Location;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RecommendationView extends JPanel implements ActionListener, PropertyChangeListener {
    private RecommendationController recommendationController;
    private RecommendationViewModel recommendationViewModel;

    public RecommendationView(RecommendationViewModel recommendationViewModel) {
        this.recommendationViewModel = recommendationViewModel;
        this.recommendationViewModel.addPropertyChangeListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Recommended Locations:"));
    }

    public void setRecommendationController(RecommendationController recommendationController) {
        this.recommendationController = recommendationController;
    }

    public void executeRecommendation(String prompt, String postalCode) {
        recommendationController.execute(prompt, postalCode);
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
        List<Location> listLocation = recommendationViewModel.getState().getRecoLocations();
        for (Location location : listLocation) {
            add(new JLabel(location.toString()));
        }


    }
}
