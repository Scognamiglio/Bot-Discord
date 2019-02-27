package fr.zheneos.astrem;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
public class autre extends BotListener {
		
		public autre(MessageReceivedEvent event) throws IOException {
			message=event.getMessage().getContentDisplay();
			
			if(message.contains("!classe")){
				verif(event);
				File repertoire = new File(lien);
				File[] files=repertoire.listFiles();
				
				String renvoi="";
				String nom,niv,xp="";
				for(int i=0;i<files.length;i++) {
					br = new BufferedReader(new FileReader(files[i]));
					br.readLine();
					nom=event.getGuild().getMemberById(files[i].toPath().getFileName().toString()).getEffectiveName();
					xp=br.readLine();
					niv=br.readLine();
					renvoi=renvoi+nom+" "+niv+" "+xp+"\n";
					br.close();
				}
				String[] tab=renvoi.split("\n");
				String retenu;
				int b;
				int a;
				for(int i=0;i<tab.length-1;i++) {
					a=Integer.parseInt(tab[i].split("niveau:")[1].split(" ")[0]);
					b=Integer.parseInt(tab[i+1].split("niveau:")[1].split(" ")[0]);
					if(a>b) {
						retenu=tab[i+1];
						tab[i+1]=tab[i];
						tab[i]=retenu;
						i=-1;
					}
					
				}
				renvoi="";
				for(int i=0;i<tab.length;i++) {
					String v=tab[tab.length-(i+1)].split(" exp")[0];
					renvoi+="**"+(i+1)+"** "+v+"\n";
				}
				
				event.getTextChannel().sendMessage(renvoi).complete();
				
			}
			


}
		public void verif(MessageReceivedEvent event) {
			File repertoire = new File(lien);
			File[] files=repertoire.listFiles();
			
			for(int i=0;i<files.length;i++) {
				try {
					event.getGuild().getMemberById(files[i].toPath().getFileName().toString()).getNickname();
				}catch(Exception e) {
					File f=new File(files[i].toString());
					f.delete();
				}
			}
		}
}