package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class ToggleSoundCommand extends Command {
    private GameWorld gw;

    public ToggleSoundCommand(GameWorld gw) {
        super("Toggle Sound");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.toggleSound();  // Call method in GameWorld to toggle sound state
    }
}
