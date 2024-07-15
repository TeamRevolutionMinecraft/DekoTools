package teamrevolution.revodeko.enums;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum CustomItems {
    COINS_BRONZE("coinsBronze", Material.SMALL_AMETHYST_BUD, "bronzeCoin"),
    COINS_SILVER("coinsSilver", Material.SMALL_AMETHYST_BUD, "silverCoin"),
    COINS_GOLD("coinsGold", Material.SMALL_AMETHYST_BUD, "goldCoin"),
    NO_ITEM("noItem", Material.STRUCTURE_VOID, "noItem");

    private ItemStack textureItem;
    private String nsKey;
    CustomItems(String customName, Material baseItem, String nsKey) {
        ItemStack itemStack = new ItemStack(baseItem);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(customName));
        itemStack.setItemMeta(itemMeta);
        textureItem = itemStack;
        this.nsKey = nsKey;
    }
    public ItemStack getTextureItem() {
        return textureItem;
    }
    public String getNsKey() {return nsKey;}
}
