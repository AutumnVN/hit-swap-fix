package autumnvn.hitSwapFix;

import autumnvn.hitSwapFix.listener.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class HitSwapFix extends JavaPlugin {

    public static Logger LOGGER;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
