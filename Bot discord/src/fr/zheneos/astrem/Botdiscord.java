package fr.zheneos.astrem;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Botdiscord {

	public static void main(String[] args) {
		String token="NDY2MDQxNjk0Nzc3NzY5OTk0.DiWSrw.amPReH-AyPDwgMHbF06ueXahdaM";
		System.out.println("a");
		try {
			JDA jda = new JDABuilder(AccountType.BOT).setToken(token).buildAsync();
			jda.addEventListener(new BotListener());
			System.out.println("ok");
		
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}
