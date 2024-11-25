package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class FightCommand extends Command {
    private GameWorld gw;

    public FightCommand(GameWorld gw) {
        super("Fight");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.collideAlienAndAstronaut();  // Trigger a fight (collision logic)
    }
}
