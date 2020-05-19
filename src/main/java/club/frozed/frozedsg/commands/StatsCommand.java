package club.frozed.frozedsg.commands;

import club.frozed.frozedsg.managers.GameManager;
import club.frozed.frozedsg.player.PlayerData;
import club.frozed.frozedsg.utils.chat.Color;
import club.frozed.frozedsg.managers.InventoryManager;
import club.frozed.frozedsg.managers.PlayerDataManager;
import club.frozed.frozedsg.utils.command.BaseCommand;
import club.frozed.frozedsg.utils.command.Command;
import club.frozed.frozedsg.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class StatsCommand extends BaseCommand {

    @Command(name = "stats")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(Color.translate("&cCorrect usage: /stats <player>."));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Color.translate(GameManager.getInstance().getGamePrefix() + "&cThat player is currently offline."));
            return;
        }

        PlayerData targetData = PlayerDataManager.getInstance().getByUUID(target.getUniqueId());
        if (targetData == null) {
            player.sendMessage(Color.translate(GameManager.getInstance().getGamePrefix() + "&cData of that player is not loaded."));
            return;
        }

        /*player.sendMessage(Color.translate("&7&m-----------------------"));
        player.sendMessage(Color.translate("&e&l" + target.getName() + "'s Statistics"));
        player.sendMessage(Color.translate("&7* &eKills&7: &6" + targetData.getKills().getAmount()));
        player.sendMessage(Color.translate("&7* &eDeaths&7: &6" + targetData.getDeaths().getAmount()));
        player.sendMessage(Color.translate("&7* &eWins&7: &6" + targetData.getWins().getAmount()));
        player.sendMessage(Color.translate("&7* &ePoints&7: &6" + targetData.getPoints().getAmount()));
        player.sendMessage(Color.translate("&7* &eGames Played&7: &6" + targetData.getGamesPlayed().getAmount()));
        player.sendMessage(Color.translate("&7* &eHighest KillStreak&7: &6" + targetData.getKillStreak().getAmount()));
        player.sendMessage(Color.translate("&7* &eKDR&7: &6" + targetData.getKdr()));
        player.sendMessage(Color.translate("&7&m-----------------------")); */
        player.openInventory(InventoryManager.getInstance().getStatsInventory(targetData));

    }
}
