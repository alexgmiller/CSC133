package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("Help");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Display help information
        Dialog.show("Help", "Key Bindings:\n"
                + "Move Left: 'l'\n"
                + "Move Right: 'r'\n"
                + "Move Up: 'u'\n"
                + "Move Down: 'd'\n"
                + "Expand Door: 'e'\n"
                + "Contract Door: 'c'\n"
                + "Game Tick: 't'", "OK", null);
    }
}
