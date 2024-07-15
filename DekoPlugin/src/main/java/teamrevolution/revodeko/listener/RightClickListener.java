package teamrevolution.revodeko.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import teamrevolution.revodeko.enums.CustomItems;
import teamrevolution.revodeko.main.RevoDeko;

import java.util.Collection;
import java.util.Objects;

public class RightClickListener implements Listener {

    public RightClickListener(RevoDeko plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    private static final @Nullable Plugin corePlugin = Bukkit.getPluginManager().getPlugin("RPCore");
    private static final double changeYToGround = 0.4;
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        ItemStack holdingItem = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && holdingItem.hasItemMeta()) {
            PersistentDataContainer container = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(corePlugin, "CustomItem");
            if (container.has(key)) {

                String itemValue = container.get(key, PersistentDataType.STRING);
                CustomItems customItem = CustomItems.NO_ITEM;

                for (CustomItems items : CustomItems.values()) {
                    if (items.getNsKey().equalsIgnoreCase(itemValue)) {
                        customItem = items;
                        break;
                    }
                }

                if (summonArmorstand(event.getPlayer(), event.getClickedBlock().getLocation(), customItem) && customItem != CustomItems.NO_ITEM) {
                    removeItemFromInventory(event.getPlayer());
                    event.getPlayer().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 0.5F, 1);
                }

            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getClickedBlock().getLocation().getNearbyEntities(1, 1, 1).isEmpty()) {
            Location location = event.getClickedBlock().getLocation();
            System.out.println(event.getClickedBlock().getLocation().getNearbyEntities(1, 1, 1));
            removeArmorstand(event.getPlayer(), location);
        }
    }
    private static boolean summonArmorstand(Player player, Location location, CustomItems headItem) {
        location.set(location.getX() + 0.5, location.getY() - changeYToGround, location.getZ() + 0.5);

        if (!location.getNearbyEntities(0.5, 0.5, 0.5).isEmpty()) {
            return false;
        }
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        stand.getEquipment().setHelmet(headItem.getTextureItem());
        stand.setMarker(true);
        stand.setGravity(false);
        stand.setInvisible(true);
        stand.setInvulnerable(true);
        stand.addScoreboardTag("DecoItem");
        return true;
    }
    private static void removeArmorstand(Player player, Location location) {
        Collection<Entity> armorStands = location.getNearbyEntities(1, 1, 1);

        armorStands.forEach(entity -> {
            if (entity.getType() == EntityType.ARMOR_STAND) {
                ArmorStand stand = (ArmorStand) entity;
                ItemStack itemStack = stand.getItem(EquipmentSlot.HEAD);
                if (itemStack.hasItemMeta()) {
                    entity.remove();
                }
            }
        });
    }
    private static void removeItemFromInventory(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        item.setAmount(item.getAmount() - 1);
    }
}
