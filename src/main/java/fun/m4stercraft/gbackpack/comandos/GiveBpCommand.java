package fun.m4stercraft.gbackpack.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiveBpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean playerFound = false;

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

        if (command.getName().equalsIgnoreCase("givebp")) {
            if (!(sender instanceof Player) && args.length == 0) {
                sender.sendMessage("Uso correto: /givebp <jogador>");
                return true;
            }

            if (args.length == 1) {
                for (Player playerToGive : Bukkit.getServer().getOnlinePlayers()) {
                    if (playerToGive.getName().equalsIgnoreCase(args[0])) {

                        //Give Chest
                        playerToGive.getInventory().addItem(chest);
                        //Give Chest

                        playerToGive.sendMessage(ChatColor.GREEN + "Recebeste uma backpack de " + sender.getName());
                        if (!(sender.getName().equalsIgnoreCase(args[0]))) {
                            sender.sendMessage(ChatColor.GREEN + playerToGive.getName().toString() + " recebeu uma backpack.");
                            return true;
                        }
                        playerFound = true;
                        return true;
                    }
                }
            }

            if (args.length == 0) {
                Player player = (Player) sender;
                player.getInventory().addItem(chest);
                player.sendMessage(ChatColor.GREEN + "Recebeste uma backpack.");
                return true;
            }

            if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "Argumentos incorretos. Uso correto: /givebp <jogador>");
                return true;
            }

            if (!playerFound) {
                sender.sendMessage(ChatColor.RED + "Esse jogador não existe.");
                return true;
            }

        }
        return false;
    }
}
