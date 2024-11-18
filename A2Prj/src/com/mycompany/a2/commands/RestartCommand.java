package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class RestartCommand extends Command {
    private GameWorld gw;

    public RestartCommand(GameWorld gw) {
        super("Restart");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.init();  // Reinitialize the game world to restart the game
        System.out.println("Game restarted");
    }
}
