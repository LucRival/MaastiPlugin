package fr.har.maasti;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    private final Integer id;
    private final String name;
    private final ItemStack itemStack;

    private List<Player> players = new ArrayList<>();

    public Quest(Integer id, String name, ItemStack itemStack){
        this.id = id;
        this.name = name;
        this.itemStack = itemStack;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Integer getId() {
        return id;
    }

    public void addPlayerComplete(Player player){
        players.add(player);
    }
    public void removePlayerComplete(Player player){
        players.remove(player);
    }
}
