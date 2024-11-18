package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class NewAlienCommand extends Command {
    private GameWorld gw;

    public NewAlienCommand(GameWorld gw) {
        super("NewAlien");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Add new alien to the game world (assuming you have a method for this)
        gw.addNewAlien();
    }
}
