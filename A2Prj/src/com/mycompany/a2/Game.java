package com.mycompany.a2;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a2.commands.*;

public class Game extends Form {
    private GameWorld gw;  // The game world (model)
    private MapView mapView;  // The map view (observer)
    private ScoreView scoreView;  // The score view (observer)

    public Game() {
        gw = new GameWorld();  // Create the GameWorld (Model)
        mapView = new MapView();  // Create the MapView (View)
        scoreView = new ScoreView();  // Create the ScoreView (View)

        // Register views as observers of GameWorld
        gw.addObserver(mapView);
        gw.addObserver(scoreView);

        // Set layout for the game
        this.setLayout(new BorderLayout());

        // Add the ScoreView at the top
        this.add(BorderLayout.NORTH, scoreView);

        // Add the MapView at the center
        this.add(BorderLayout.CENTER, mapView);

        // Create toolbar for the title bar
        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("The Rescue Game");

        // Set Toolbar as global (optional)
        Toolbar.setGlobalToolbar(true);

        // Add Help command to the right side of the toolbar
        toolbar.addCommandToRightBar(new HelpCommand());

        // Add side menu options
        addCommandsToMenu(toolbar);

        // Add left, right, and bottom button containers
        this.add(BorderLayout.WEST, createLeftButtonContainer());
        this.add(BorderLayout.EAST, createRightButtonContainer());
        this.add(BorderLayout.SOUTH, createBottomButtonContainer());

        // Initialize the game world and start the game
        gw.init();
    }

    // Add Commands to Side Menu
    private void addCommandsToMenu(Toolbar toolbar) {
        toolbar.addCommandToSideMenu(new RestartCommand(gw));
        toolbar.addCommandToSideMenu(new ToggleSoundCommand(gw));
        toolbar.addCommandToSideMenu(new HelpCommand());
        toolbar.addCommandToSideMenu(new ExitCommand());
    }

    // Create a container for buttons on the left side
    private Container createLeftButtonContainer() {
        Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        leftContainer.getAllStyles().setPadding(10, 10, 10, 10);  // Padding around the side panel

        Button expandButton = new Button("Expand");
        Button upButton = new Button("Up");
        Button leftButton = new Button("Left");
        Button moveToAstronautButton = new Button("MoveToAstronaut");

        // Apply styles to each button
        applyButtonStyles(expandButton);
        applyButtonStyles(upButton);
        applyButtonStyles(leftButton);
        applyButtonStyles(moveToAstronautButton);

        // Set commands for each button
        expandButton.setCommand(new ExpandDoorCommand(gw));
        upButton.setCommand(new MoveUpCommand(gw));
        leftButton.setCommand(new MoveLeftCommand(gw));
        moveToAstronautButton.setCommand(new MoveAstronautCommand(gw));

        // Add buttons to the left container
        leftContainer.addAll(expandButton, upButton, leftButton, moveToAstronautButton);

        return leftContainer;
    }

    // Create a container for buttons on the right side
    private Container createRightButtonContainer() {
        Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        rightContainer.getAllStyles().setPadding(10, 10, 10, 10);  // Padding around the side panel

        Button contractButton = new Button("Contract");
        Button downButton = new Button("Down");
        Button rightButton = new Button("Right");
        Button moveToAlienButton = new Button("MoveToAlien");
        Button scoreButton = new Button("Score");

        // Apply styles to each button
        applyButtonStyles(contractButton);
        applyButtonStyles(downButton);
        applyButtonStyles(rightButton);
        applyButtonStyles(moveToAlienButton);
        applyButtonStyles(scoreButton);

        // Set commands for each button
        contractButton.setCommand(new ContractDoorCommand(gw));
        downButton.setCommand(new MoveDownCommand(gw));
        rightButton.setCommand(new MoveRightCommand(gw));
        moveToAlienButton.setCommand(new MoveAlienCommand(gw));
        scoreButton.setCommand(new ScoreCommand(gw));

        // Add buttons to the right container
        rightContainer.addAll(contractButton, downButton, rightButton, moveToAlienButton, scoreButton);

        return rightContainer;
    }

    // Create a container for buttons at the bottom
    private Container createBottomButtonContainer() {
        Container bottomContainer = new Container(new FlowLayout(CENTER));
        bottomContainer.getAllStyles().setPadding(10, 10, 10, 10);  // Padding around the bottom panel

        Button newAlienButton = new Button("NewAlien");
        Button fightButton = new Button("Fight");
        Button tickButton = new Button("Tick");

        // Apply styles to each button
        applyButtonStyles(newAlienButton);
        applyButtonStyles(fightButton);
        applyButtonStyles(tickButton);

        // Set commands for each button
        newAlienButton.setCommand(new NewAlienCommand(gw));
        fightButton.setCommand(new FightCommand(gw));
        tickButton.setCommand(new GameTickCommand(gw));

        // Add buttons to the bottom container
        bottomContainer.addAll(newAlienButton, fightButton, tickButton);

        return bottomContainer;
    }

    // Helper method to apply consistent styles to each button
    private void applyButtonStyles(Button button) {
        // Set background color to blue
        button.getAllStyles().setBgColor(0x0000FF);  // Blue background
        button.getAllStyles().setFgColor(0xFFFFFF);  // White text color
        button.getAllStyles().setBgTransparency(255); // Fully opaque for the background

        // Set padding and margins for spacing
        button.getAllStyles().setPadding(8, 8, 8, 8);  // Reduced padding: top, bottom, left, right
        button.getAllStyles().setMargin(3, 3, 0, 0);  // Reduced margin between buttons

        // Set a white border to make the button stand out
        button.getAllStyles().setBorder(Border.createLineBorder(2, 0xFFFFFF));  // White border

        // Make the buttons smaller by adjusting the preferred size
        button.setPreferredSize(new Dimension(300, 200));  // Smaller size: 80px wide, 50px tall
    }

}
