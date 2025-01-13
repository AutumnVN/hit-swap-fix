package autumnvn.hitSwapNerf;

import autumnvn.hitSwapNerf.listener.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HitSwapNerf extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("HitSwapNerf has been enabled!");
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HitSwapNerf has been disabled!");
    }
}
