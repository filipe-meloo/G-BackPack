package fun.m4stercraft.gbackpack.eventos;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RightClickBackpack implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        //Criar o baú
        ItemStack chest = new ItemStack(Material.CHEST_MINECART);
        ItemMeta chestmeta = chest.getItemMeta();
        chestmeta.setDisplayName(ChatColor.GOLD + "Mochila");
        ArrayList<String> chestlore = new ArrayList<>();
        chestlore.add("");
        chestlore.add(ChatColor.GRAY + "Uma mochila!");
        chestlore.add(ChatColor.GRAY + "(Clica com o botão direito!)");
        chestlore.add("");
        chestmeta.setLore(chestlore);
        chest.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        chestmeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        chestmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chest.setItemMeta(chestmeta);

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getInventory().getItemInMainHand().equals(chest)) {
                event.setCancelled(true);
                player.performCommand("backpack");
            }
        }
    }
}
