package fr.zheneos.astrem;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;

public class Bmsg extends BotListener {
	
public Bmsg(MessageReceivedEvent event) {
	event.getTextChannel().sendMessage("a"+lien).complete();
}
}
