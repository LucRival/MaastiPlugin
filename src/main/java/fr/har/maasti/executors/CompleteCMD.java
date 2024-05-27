package fr.har.maasti.executors;

import fr.har.maasti.Maasti;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CompleteCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        assert commandSender instanceof Player;
        commandSender.sendMessage("DEBUG01");
        if(strings.length == 2){
            commandSender.sendMessage("DEBUG02");
            if(Bukkit.getPlayer(strings[0]) != null){
                commandSender.sendMessage("DEBUG03");
                if(Integer.parseInt(strings[1]) > 0){
                    commandSender.sendMessage("DEBUG04");
                    int id = Integer.parseInt(strings[1]);
                    if(fr.har.maasti.Maasti.QUEST.exist(id)){
                        commandSender.sendMessage("DEBUG05");
                        fr.har.maasti.Maasti.QUEST.completeQuest(((Player) commandSender).getPlayer(), fr.har.maasti.Maasti.QUEST.getQuest(id));
                    }
                }
            }
        }
        return false;
    }
}
