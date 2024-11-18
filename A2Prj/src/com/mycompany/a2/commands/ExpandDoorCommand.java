package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class ExpandDoorCommand extends Command {
    private GameWorld gw;

    public ExpandDoorCommand(GameWorld gw) {
        super("Expand Door");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.expandDoor();  // Call the expandDoor method in GameWorld
    }
}
