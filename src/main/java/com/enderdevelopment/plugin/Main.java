package com.enderdevelopment.plugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Main extends JavaPlugin implements CommandExecutor {
    private static final int BOOK_LEVEL = 1000;
    private static final int AXE_FORTUNE_LEVEL = 101;

    @Override
    public void onEnable() {
        if (getCommand("encantamentos") != null) {
            getCommand("encantamentos").setExecutor(this);
        }
        getLogger().info("Enchantments1000 enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Enchantments1000 disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("This command can only be used by a player.", NamedTextColor.RED));
            return true;
        }
        if (args.length == 0) {
            sendUsage(player, label);
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "livros", "books" -> giveBooks(player);
            case "itens", "items" -> giveCustomItems(player);
            case "todos", "all" -> {
                giveBooks(player);
                giveCustomItems(player);
            }
            default -> sendUsage(player, label);
        }
        return true;
    }

    private void sendUsage(Player player, String label) {
        player.sendMessage(Component.text("Use /" + label + " <livros|itens|todos>.", NamedTextColor.YELLOW));
    }

    private void giveBooks(Player player) {
        List<Enchantment> enchantments = new ArrayList<>();
        for (Enchantment enchantment : Registry.ENCHANTMENT) {
            enchantments.add(enchantment);
        }
        enchantments.sort(Comparator.comparing(enchantment -> enchantment.getKey().asString()));
        for (Enchantment enchantment : enchantments) {
            giveOrDrop(player, createEnchantedBook(enchantment));
        }
        player.sendMessage(Component.text("You received " + enchantments.size() + " enchanted books at level 1000.", NamedTextColor.GREEN));
    }

    private ItemStack createEnchantedBook(Enchantment enchantment) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta rawMeta = book.getItemMeta();
        if (!(rawMeta instanceof EnchantmentStorageMeta meta)) {
            throw new IllegalStateException("Could not create enchanted book metadata.");
        }
        meta.addStoredEnchant(enchantment, BOOK_LEVEL, true);
        book.setItemMeta(meta);
        return book;
    }

    private void giveCustomItems(Player player) {
        ItemStack stick = new ItemStack(Material.STICK);
        addEnchantment(stick, vanillaEnchantment("knockback"), BOOK_LEVEL);
        giveOrDrop(player, stick);

        ItemStack shovel = new ItemStack(Material.WOODEN_SHOVEL);
        addEnchantment(shovel, vanillaEnchantment("fortune"), BOOK_LEVEL);
        giveOrDrop(player, shovel);

        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
        addEnchantment(sword, vanillaEnchantment("sharpness"), BOOK_LEVEL);
        addEnchantment(sword, vanillaEnchantment("fire_aspect"), BOOK_LEVEL);
        giveOrDrop(player, sword);

        ItemStack axe = new ItemStack(Material.NETHERITE_AXE);
        addEnchantment(axe, vanillaEnchantment("fortune"), AXE_FORTUNE_LEVEL);
        giveOrDrop(player, axe);
        player.sendMessage(Component.text("You received the four custom items.", NamedTextColor.GREEN));
    }

    private Enchantment vanillaEnchantment(String key) {
        Enchantment enchantment = Registry.ENCHANTMENT.get(NamespacedKey.minecraft(key));
        if (enchantment == null) {
            throw new IllegalStateException("Missing vanilla enchantment: " + key);
        }
        return enchantment;
    }

    private void addEnchantment(ItemStack item, Enchantment enchantment, int level) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            throw new IllegalStateException("Missing item metadata for " + item.getType());
        }
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
    }

    private void giveOrDrop(Player player, ItemStack item) {
        player.getInventory().addItem(item).values().forEach(dropped ->
                player.getWorld().dropItemNaturally(player.getLocation(), dropped));
    }
}
