package me.yochran.yowarps.gui.guis;

import me.yochran.yowarps.gui.Button;
import me.yochran.yowarps.gui.CustomGUI;
import me.yochran.yowarps.gui.GUI;
import me.yochran.yowarps.utils.ItemBuilder;
import me.yochran.yowarps.utils.XMaterial;
import me.yochran.yowarps.yoWarps;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class FindPageGUI extends CustomGUI {

    public FindPageGUI(Player player, int size, String title) {
        super(player, size, title);
    }

    public void setup(String type, int pages) {
        ItemBuilder itemBuilder = new ItemBuilder(XMaterial.BOOK.parseItem(), 1, "&4&lNULL", new ArrayList<>());

        for (int i = 0; i < pages; i++) {
            int page = i;
            gui.setButton(i, new Button(
                    itemBuilder.getItem(),
                    () -> {
                        GUI.close(gui);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (type.equalsIgnoreCase("Warps")) {
                                    WarpsGUI warpsGUI = new WarpsGUI(getGui().getPlayer(), 18, "&8Warps List:");
                                    warpsGUI.setup(page + 1);
                                    GUI.open(warpsGUI.getGui());
                                }
                            }
                        }.runTaskLater(yoWarps.getInstance(), 1);
                    },
                    "&a&lPage " + (i + 1),
                    itemBuilder.getLore()
            ));
        }
    }
}
