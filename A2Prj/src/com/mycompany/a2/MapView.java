package com.mycompany.a2;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import java.util.Observable;
import java.util.Observer;

public class MapView extends Container implements Observer {

    public MapView() {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));  // Vertical layout

        // Set a red border around the MapView
        this.getAllStyles().setBorder(Border.createLineBorder(4, 0xFF0000));  // 4px red border

        // Optionally, set padding or margin to ensure the border is visible and spaced out
        this.getAllStyles().setPadding(10, 10, 10, 10);  // Optional padding inside the border
        this.getAllStyles().setMargin(10, 10, 10, 10);  // Optional margin outside the border

        this.add(new Label("Map View"));  // Example label, can be removed if not needed
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameWorld) {
            GameWorld gw = (GameWorld) o;
            this.removeAll();  // Clear previous output
            IIterator iterator = gw.getGameObjects().getIterator();
            while (iterator.hasNext()) {
                GameObject obj = iterator.getNext();
                this.add(new Label(obj.toString()));  // Update with game object info
            }
            this.revalidate();  // Refresh the view
        }
    }
}
