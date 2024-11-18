package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // Show confirmation dialog before exiting
        boolean confirmExit = Dialog.show("Exit", "Are you sure you want to exit?", "Yes", "No");
        if (confirmExit) {
            System.exit(0);  // Exit the application
        }
    }
}
