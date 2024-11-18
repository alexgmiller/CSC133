package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MoveUpCommand extends Command {
    private GameWorld gw;

    public MoveUpCommand(GameWorld gw) {
        super("Move Up");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveUp();  // Call the moveUp method in GameWorld
    }
}
