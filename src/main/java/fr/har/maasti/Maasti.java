package fr.har.maasti;

import fr.har.maasti.events.QuestCompleteEvent;
import fr.har.maasti.executors.CompleteCMD;
import fr.har.maasti.listeners.onQuestComplete;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Maasti extends JavaPlugin {

    private static Maasti instance;
    private static ConsoleCommandSender consoleCommandSender;

    public static QUEST QUEST = new QUEST();

    @Override
    public void onEnable() {
        instance = this;
        consoleCommandSender = Bukkit.getConsoleSender();

        getServer().getPluginManager().registerEvents(new onQuestComplete(), this);

        loadQuests();
        loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadQuests(){
        QUEST.addQuest(new Quest(1, "FIRST",new ItemStack(Material.IRON_INGOT, 5)));
        QUEST.addQuest(new Quest(2, "SECOND",new ItemStack(Material.GOLD_INGOT, 5)));
        QUEST.addQuest(new Quest(3, "THIRD",new ItemStack(Material.DIAMOND, 5)));
        QUEST.addQuest(new Quest(4, "THIRD",new ItemStack(Material.EMERALD, 5)));
    }

    private void loadCommands(){
        getCommand("complete").setExecutor(new CompleteCMD());
    }

    public static ConsoleCommandSender getConsoleCommandSender() {
        return consoleCommandSender;
    }
    public static Maasti getInstance() {
        return instance;
    }

    public static class QUEST {

        private final List<Quest> questList = new ArrayList<>();

        public void completeQuest(Player player, Quest quest){
            if(!exist(quest)){return;}
            if(!hasComplete(player,quest)){
                QuestCompleteEvent event = new QuestCompleteEvent(
                        player,
                        quest
                );
                Bukkit.getServer().getPluginManager().callEvent(event);
                if(!event.isCancelled()){
                    reward(player,quest);
                    quest.addPlayerComplete(player);
                }
            }
        }

        public boolean exist(Quest quest){
            return questList.contains(quest);
        }
        public boolean exist(Integer id){
            return getQuest(id) != null;
        }
        public boolean exist(String name){
            return getQuest(name) != null;
        }

        public Quest getQuest(Integer id){
            for (Quest quest : questList){
                if(Objects.equals(quest.getId(), id)){
                    return quest;
                }
            }
            return null;
        }
        public Quest getQuest(String name){
            for (Quest quest : questList){
                if(Objects.equals(quest.getName(), name)){
                    return quest;
                }
            }
            return null;
        }

        public boolean hasComplete(Player player, Quest quest){
            return quest.getPlayers().contains(player);
        }
        public boolean hasComplete(Player player, Integer id){
            if(getQuest(id) == null){return false;}
            return getQuest(id).getPlayers().contains(player);
        }
        public boolean hasComplete(Player player, String name){
            if(getQuest(name) == null){return false;}
            return getQuest(name).getPlayers().contains(player);
        }

        private void reward(Player player, Quest quest){
            player.getInventory().addItem(quest.getItemStack());
        }

        public void addQuest(Quest quest){
            questList.add(quest);
        }
        public void removeQuest(Quest quest){
            questList.remove(quest);
        }
    }
}
