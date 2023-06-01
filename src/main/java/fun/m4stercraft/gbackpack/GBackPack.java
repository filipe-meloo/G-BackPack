package fun.m4stercraft.gbackpack;

import fun.m4stercraft.gbackpack.comandos.BackpackCommand;
import fun.m4stercraft.gbackpack.comandos.GiveBpCommand;
import fun.m4stercraft.gbackpack.eventos.PutBackPack;
import fun.m4stercraft.gbackpack.eventos.RightClickBackpack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GBackPack extends JavaPlugin {

    public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new BackpackCommand(), this);
        getCommand("mochila").setExecutor(new BackpackCommand());
        getServer().getPluginManager().registerEvents(new RightClickBackpack(), this);
        getCommand("givebp").setExecutor(new GiveBpCommand());
        getServer().getPluginManager().registerEvents(new PutBackPack(), this);
        this.saveDefaultConfig(); //Criar config

        if (this.getConfig().contains("data")) {
            this.restoreInvs();
            this.getConfig().set("data", null);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if (!menus.isEmpty()) {
            this.saveInvs();
        }
    }

    public void saveInvs() {
        for (Map.Entry<String, ItemStack[]> entry : menus.entrySet()) {
            this.getConfig().set("data." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();
    }

    public void restoreInvs() {
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key ->{
            ItemStack[] content = ((List<ItemStack>) this.getConfig().get("data." + key)).toArray(new ItemStack[0]);
            menus.put(key, content);
        });
    }

}
