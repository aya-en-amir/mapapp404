package view;

import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import entity.Location;

public class MapView extends JFrame {

    public MapView(List<Location> locations) {
        setTitle("Map View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JXMapViewer mapViewer = new JXMapViewer();

        OSMTileFactoryInfo tileFactoryInfo = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(tileFactoryInfo);
        mapViewer.setTileFactory(tileFactory);

        if (!locations.isEmpty()) {
            Location first = locations.get(0);
            GeoPosition center = new GeoPosition(first.getLatitude(), first.getLongitude());
            mapViewer.setZoom(5);
            mapViewer.setAddressLocation(center);
        }

        // display red dots to show each location on the map.
        Set<Waypoint> waypoints = new HashSet<>();
        for (Location loc : locations) {
            waypoints.add(new DefaultWaypoint(new GeoPosition(loc.getLatitude(), loc.getLongitude())));
        }

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        mapViewer.setOverlayPainter(waypointPainter);

        add(mapViewer, BorderLayout.CENTER);
        setVisible(true);
    }
}
