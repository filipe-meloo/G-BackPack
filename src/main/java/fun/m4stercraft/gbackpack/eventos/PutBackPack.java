package fun.m4stercraft.gbackpack.eventos;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PutBackPack implements Listener {

    //Verificar se ele coloca a mochila dentro do inventário
    @EventHandler
    public void ColocaMochila(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        HumanEntity player = event.getWhoClicked();
        String inv = event.getView().getTitle();

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

        if (inv.equals("BackPack de " + player.getName())) {
            if (item.equals(chest)) {
                //Cancelar evento
                event.setCancelled(true);
            }
        }
    }
}
