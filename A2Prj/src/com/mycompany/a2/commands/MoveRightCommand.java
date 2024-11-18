package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MoveRightCommand extends Command {
    private GameWorld gw;

    public MoveRightCommand(GameWorld gw) {
        super("Move Right");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveRight();  // Call the moveRight method in GameWorld
    }
}
