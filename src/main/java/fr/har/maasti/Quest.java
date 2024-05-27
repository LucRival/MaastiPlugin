package fr.har.maasti;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Quest {
    private final Integer id;
    private final String name;
    private final ItemStack itemStack;

    private List<Player> players;

    public Quest(Integer id, String name, ItemStack itemStack){
        this.id = id;
        this.name = name;
        this.itemStack = itemStack;

        if(fr.har.maasti.Maasti.QUEST.exist(id)){return;}
        if(fr.har.maasti.Maasti.QUEST.exist(name)){return;}

        Maasti.QUEST.addQuest(this);
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
}
