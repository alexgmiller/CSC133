package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MoveAlienCommand extends Command {
    private GameWorld gw;

    // Constructor accepting GameWorld as a parameter
    public MoveAlienCommand(GameWorld gw) {
        super("MoveToAlien");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.transferSpaceshipToAlien();  // Move the spaceship to an alien's location
    }
}
