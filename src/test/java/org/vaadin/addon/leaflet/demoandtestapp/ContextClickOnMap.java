package org.vaadin.addon.leaflet.demoandtestapp;

import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.shared.Point;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import org.vaadin.addon.leaflet.LMarker;
import org.vaadin.addon.leaflet.LOpenStreetMapLayer;
import org.vaadin.addon.leaflet.LPolygon;
import org.vaadin.addon.leaflet.LeafletClickEvent;
import org.vaadin.addon.leaflet.LeafletClickListener;
import org.vaadin.addon.leaflet.LeafletContextMenuEvent;
import org.vaadin.addon.leaflet.LeafletContextMenuListener;
import org.vaadin.addonhelpers.AbstractTest;

public class ContextClickOnMap extends AbstractTest {

	@Override
	public String getDescription() {
		return "Testing Context click events";
	}

	private LMap leafletMap;

	@Override
	public Component getTestComponent() {
		
		leafletMap = new LMap();
        final LOpenStreetMapLayer lOpenStreetMapLayer = new LOpenStreetMapLayer();
        
        leafletMap.addLayer(lOpenStreetMapLayer);
        
		leafletMap.setCenter(0, 0);
		leafletMap.setZoomLevel(2);
        

        LPolygon polygon = new LPolygon(new Point(0, 0), new Point(30, 30), new Point(0,30));
        
        leafletMap.addLayer(polygon);
        
        polygon.addContextMenuListener(new LeafletContextMenuListener() {
            @Override
            public void onContextMenu(LeafletContextMenuEvent event) {
                
                Notification.show("CxtClick at polygon at " + event.getPoint().toString());
                
            }
        });
        
        polygon.addClickListener(new LeafletClickListener() {
            @Override
            public void onClick(LeafletClickEvent event) {
                Notification.show("Std Click at polygon at " + event.getPoint().toString());
            }
        });
        
        leafletMap.addContextMenuListener(new LeafletContextMenuListener() {
            @Override
            public void onContextMenu(LeafletContextMenuEvent event) {
                
                Point point = event.getPoint();
                
                LMarker marker = new LMarker(point);
                marker.setPopup("Created by context click on LMap");
                leafletMap.addComponent(marker);
                marker.openPopup();
                
            }
        });

        
        leafletMap.addClickListener(new LeafletClickListener() {
            @Override
            public void onClick(LeafletClickEvent event) {
                Point point = event.getPoint();
                
                LMarker marker = new LMarker(point);
                marker.setPopup("Created by std click on lOpenStreetMapLayer");
                leafletMap.addComponent(marker);
                marker.openPopup();
            }
        });
                
		return leafletMap;
	}
}
