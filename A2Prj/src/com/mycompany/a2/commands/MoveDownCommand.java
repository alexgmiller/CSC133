package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MoveDownCommand extends Command {
    private GameWorld gw;

    public MoveDownCommand(GameWorld gw) {
        super("Move Down");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.moveDown();  // Call the moveDown method in GameWorld
    }
}
