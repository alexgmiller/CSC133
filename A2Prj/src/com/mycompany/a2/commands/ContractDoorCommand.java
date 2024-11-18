package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class ContractDoorCommand extends Command {
    private GameWorld gw;

    public ContractDoorCommand(GameWorld gw) {
        super("Contract Door");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.contractDoor();  // Call the contractDoor method in GameWorld
    }
}
