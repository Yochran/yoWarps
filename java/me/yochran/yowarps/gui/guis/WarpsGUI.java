package me.yochran.yowarps.gui.guis;
import me.yochran.yowarps.gui.*;
import me.yochran.yowarps.management.Warp;
import me.yochran.yowarps.utils.ItemBuilder;
import me.yochran.yowarps.utils.Utils;
import me.yochran.yowarps.utils.XMaterial;
import me.yochran.yowarps.yoWarps;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.*;

public class WarpsGUI extends CustomGUI implements PagedGUI {

    public WarpsGUI(Player player, int size, String title) {
        super(player, size, title);
    }

    @Override
    public void setupPagedGUI(Map<Integer, Button> buttons, int page) {
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            int[] data = Utils.getSlotData(entry.getKey());
            if (page == data[0])
                gui.setButton(data[1] + 9, entry.getValue());
        }
    }

    public void setup(int page) {
        Map<Integer, Button> buttons = new HashMap<>();
        Set<Integer> pages = new HashSet<>();

        if (Warp.getWarps().size() > 0) {
            int loop = -1;
            for (Warp warp : Warp.getWarps()) {
                loop++;
                DecimalFormat df = new DecimalFormat("###############.##");

                ItemBuilder itemBuilder = new ItemBuilder(XMaterial.OAK_SIGN.parseItem(), 1, "&b" + warp.getName(),
                        ItemBuilder.formatLore(new String[] {
                                        "&3&m---------------------",
                                        "&bWorld: &3" + warp.getLocation().getWorld().getName(),
                                        "&bX: &3" + df.format(warp.getLocation().getX()),
                                        "&bY: &3" + df.format(warp.getLocation().getX()),
                                        "&bZ: &3" + df.format(warp.getLocation().getX()),
                                        "&3&m---------------------"
                                })
                );

                buttons.put(loop, new Button(
                        itemBuilder.getItem(),
                        () -> {
                            GUI.close(gui);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    getGui().getPlayer().performCommand("warp " + warp.getName());
                                }
                            }.runTaskLater(yoWarps.getInstance(), 1);
                        },
                        itemBuilder.getName(),
                        itemBuilder.getLore()
                ));
            }

            for (Map.Entry<Integer, Button> entry : buttons.entrySet()) pages.add((entry.getKey() / 9) + 1);

            Toolbar toolbar = new Toolbar(getGui(), "Warps", page, new ArrayList<>(pages), () -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        WarpsGUI warpsGUI = new WarpsGUI(getGui().getPlayer(), 18, "&8Warps List:");
                        warpsGUI.setup(Toolbar.getNewPage().get());
                        GUI.open(warpsGUI.getGui());
                    }
                }.runTaskLater(yoWarps.getInstance(), 1);
            });

            toolbar.create();
            setupPagedGUI(buttons, page);
        }
    }
}
