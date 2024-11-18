package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MoveAstronautCommand extends Command {
    private GameWorld gw;

    // Constructor to pass in the GameWorld
    public MoveAstronautCommand(GameWorld gw) {
        super("MoveToAstronaut");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.transferSpaceshipToAstronaut();  // Move the spaceship to an astronaut's location
    }
}
