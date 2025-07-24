package view;

import entity.Location;

import javax.swing.*;
import java.util.List;

public class ResultView extends JPanel {
    public ResultView(List<Location> locations) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Recommended Locations:"));

        for (Location loc : locations) {
            add(new JLabel(loc.toString()));
        }
    }
}