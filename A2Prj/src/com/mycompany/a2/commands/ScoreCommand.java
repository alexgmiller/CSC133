package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class ScoreCommand extends Command {
    private GameWorld gw;

    public ScoreCommand(GameWorld gw) {
        super("Score");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.printGameState();  // Print the current score and game state
    }
}
