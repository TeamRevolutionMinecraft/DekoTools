package teamrevolution.revodeko.main;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import teamrevolution.revodeko.listener.RightClickListener;

public final class RevoDeko extends JavaPlugin {


    public static Plugin plugin;
    @Override

    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);
        new RightClickListener(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        RevoDeko.plugin = plugin;
    }
}
