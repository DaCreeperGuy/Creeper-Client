package me.spruce.creeperclient.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.spruce.creeperclient.command.commands.BaritoneCommand;
import me.spruce.creeperclient.command.commands.HelpCommand;
import me.spruce.creeperclient.command.commands.PrefixCommand;
import me.spruce.creeperclient.command.commands.WaterMarkCommand;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.CopyOnWriteArrayList;

import static me.spruce.creeperclient.module.Module.mc;

public class CommandManager {
    public CopyOnWriteArrayList<Command> commands;
    public String prefix = ".";

    public CommandManager() {
        commands = new CopyOnWriteArrayList<>();

        commands.add(new HelpCommand());

        commands.add(new WaterMarkCommand());
        commands.add(new PrefixCommand());
        commands.add(new BaritoneCommand());
    }

    @SubscribeEvent
    public void onMessageSent(ClientChatEvent event) {
        if (event.getMessage().startsWith(prefix)) {
            event.setCanceled(true);
            mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            final boolean[] success = {false};
            commands.forEach(command -> {
                if (event.getMessage().startsWith(prefix + command.name)) {
                    command.onCall(event);
                    success[0] = true;
                }
            });
            if (!success[0]) {
                Command.sendMessage(ChatFormatting.DARK_RED + "Invalid input!");
                new HelpCommand().onCall(event);
            }
        }
    }
}
