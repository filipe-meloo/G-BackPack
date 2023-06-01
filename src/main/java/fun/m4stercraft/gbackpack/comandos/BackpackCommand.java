package fun.m4stercraft.gbackpack.comandos;

import fun.m4stercraft.gbackpack.GBackPack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BackpackCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Chest BACKPACK
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
        //Chest BACKPACK

        if (command.getName().equalsIgnoreCase("mochila")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("A consola não pode executar este comando.");
                return true;
            }

            boolean playerFound = false;

            if (args.length == 0) {
                Player player = (Player) sender;
                if (player.getInventory().contains(chest)) {

                    Inventory inv = Bukkit.createInventory(player, 54, "BackPack de " + player.getName());

                    //Verificar se existe no Mapa
                    if (GBackPack.menus.containsKey(player.getUniqueId().toString()))
                        inv.setContents(GBackPack.menus.get(player.getUniqueId().toString()));

                    player.openInventory(inv);
                    player.sendMessage(ChatColor.GREEN + "Abriste a tua mochila!");
                    return true;

                } else {
                    sender.sendMessage(ChatColor.RED + "Precisas de ter a tua mochila no inventário para a abrires.");
                    return true;
                }
            }

            if (args.length == 1) {
                for (Player playerToWatch : Bukkit.getServer().getOnlinePlayers()) {
                    if (playerToWatch.getName().equalsIgnoreCase(args[0])) {

                        Player player = (Player) sender;
                        //Abrir BackPack
                        Inventory inv = Bukkit.createInventory(player, 54, "BackPack de " + playerToWatch.getName());

                        //Verificar se existe no Mapa
                        if (GBackPack.menus.containsKey(playerToWatch.getUniqueId().toString()))
                            inv.setContents(GBackPack.menus.get(playerToWatch.getUniqueId().toString()));

                        player.openInventory(inv);
                        player.sendMessage(ChatColor.GREEN + "Abriste a mochila de " + ChatColor.YELLOW + playerToWatch.getName() + ChatColor.GREEN + "!");

                        playerFound = true;
                        return true;
                    }
                }

                if (!playerFound) {
                    //Outro ciclo para ver jogadores offiline
                    for (OfflinePlayer playerToWatch : Bukkit.getServer().getOfflinePlayers()) {
                        if (playerToWatch.getName().equalsIgnoreCase(args[0])) {

                            Player player = (Player) sender;
                            //Abrir BackPack
                            Inventory inv = Bukkit.createInventory(player, 54, "BackPack de " + playerToWatch.getName());

                            //Verificar se existe no Mapa
                            if (GBackPack.menus.containsKey(playerToWatch.getUniqueId().toString()))
                                inv.setContents(GBackPack.menus.get(playerToWatch.getUniqueId().toString()));

                            player.openInventory(inv);
                            player.sendMessage(ChatColor.GREEN + "Abriste a mochila de " + ChatColor.YELLOW + playerToWatch.getName() + ChatColor.GREEN + "!");

                            playerFound = true;
                            return true;
                        }
                    }
                }

                if (!playerFound) {
                    sender.sendMessage(ChatColor.RED + "Argumentos incorretos. Uso correto: /bp <jogador> para veres a mochila dele.");
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains("BackPack de " + event.getPlayer().getName())) {
            GBackPack.menus.put(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
        }
    }
}
