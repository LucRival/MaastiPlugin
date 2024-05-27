package fr.har.maasti.listeners;

import fr.har.maasti.events.QuestCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onQuestComplete implements Listener {

    @EventHandler
    public void onQuestCompleteEvent(QuestCompleteEvent event){
        Bukkit.broadcastMessage(
                "§aLe joueur §2" +
                event.getPlayer() +
                "§a vient de terminer la quête §5#" +
                event.getQuest().getId() +
                "§a'§2" +
                event.getQuest().getName() +
                "§a'.");
    }
}
