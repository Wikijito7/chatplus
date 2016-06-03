package es.wiky.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Eventos implements Listener {
	private Main plugin;

	public Eventos(Main instance){

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();

		if (!Main.config.contains("User." + p.getName())) {
			List<String> h = new ArrayList<String>();

			h.add(p.getName());

			Main.config.set("Users", h);
			Main.config.set("Users." + p.getName() + ".cnick", "none");
			try {
				Main.config.save(Main.file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent event){
		Player p = event.getPlayer();

		if (event.getMessage().equalsIgnoreCase("%")) {
			event.setFormat(event.getFormat());
			return;
		}
		
		String format = Mensajes.playerDisplayName(p.getDisplayName()) + event.getFormat();
		event.setFormat(format);
		

	}
}
