package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class GameTickCommand extends Command {
    private GameWorld gw;

    public GameTickCommand(GameWorld gw) {
        super("Tick");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.gameTick();  // Call the gameTick method in GameWorld
    }
}
