package fr.zheneos.astrem;


import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Member;

import java.io.*;
import java.util.List;

public class BotListener implements EventListener {

	@Override
	public void onEvent(Event event) {
			
		if(event instanceof MessageReceivedEvent)
			try {
				onMsg((MessageReceivedEvent)event);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

	}
	int[][] race= {{0,0,0,0},{0,10,-10,0},{-50,5,5,-1},{50,5,0,-2},{0,5,-10,5},{-50,5,-5,5}};
	String[] nrace= {"504772349598367744","504863160004640768","504863199766511626","504863313008787456","504772033675001876"};
	int[][] classe={{0,0,0,0},{0,15,0,1},{0,10,0,2},{50,10,5,0},{50,5,5,1},{250,-10,15,-2},{250,-10,15,-2},{250,-10,15,-2},{0,15,-10,3},{0,20,-10,2},{-100,30,-10,2},{0,20,-10,2},{100,-10,-10,2},{100,-10,-10,2},{100,-10,-10,2},{100,-10,-10,2},{-100,20,0,2},{-100,10,0,4},{-100,20,0,2}};
	String[] nclasse={"513565354362929155","513565421220397056","513565475330850818","513565538354462722","513565632218923018","513565646911700995","513565692591603715","513565722299990018","513565761214742530","513565795893116929","513565832857780224","513565934380908548","513565979104772107","513566008364236801","513566046347591681","513566142200152074","513566158537097269","513566202300465182"};
	
	BufferedReader br;
	BufferedWriter writer;
	String id="";
	String lien="/home/ec2-user/joueur/";
	String message;
	
	private void onMsg(MessageReceivedEvent event) throws IOException {
		if(event.getAuthor().equals(event.getJDA().getSelfUser())) return;
		if(event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
			message=event.getMessage().getContentDisplay();

			
			if(message.contains("!level") || message.contains("!xpadd") ||message.contains("!stop") || message.contains("!add-event") || message.contains("!end-event")) {
				new Gestion(event);	
			}
			else if(message.contains("!classe") || message.contains("!verif")){
				new autre(event);
			}
	
			else if(message.contains("!donjon") || message.contains("!pdv") || message.contains("!degat") || message.contains("```xml\n")) {
				new Donjon(event);
			}
			else if(message.contains("!stat")||message.contains("!upgrade")) {
				new stat(event);
			}
	
			else {
					
					if(event.getMember().getRoles().toString().contains("RP")) {
						id=event.getMember().getUser().getId();
						if(!new File(lien+id).exists()) {
							event.getGuild().getController().addSingleRoleToMember(event.getGuild().getMemberById(id), event.getGuild().getRoleById("456269567535349791")).complete();
							writer = new BufferedWriter(new FileWriter(new File(lien+id)));
							writer.close();
							writer = new BufferedWriter(new FileWriter(new File(lien+id)));
							writer.write(event.getAuthor().getName()+"\nexperience:0\nniveau:10\nCaractéristique\nPoints à distribuer:6\nPoints de vie:250\nAttaque:25\ndéfense:25\nTechnique:1\n\nskill\n");
							writer.close();
						}
						if(!event.getGuild().getCategoryById("514892197779341315").getTextChannels().toString().contains(event.getMember().getEffectiveName().toString().split(" ")[0].toLowerCase())){
							List<Permission> a=event.getGuild().getTextChannelById("523993332045709342").getPermissionOverride(event.getGuild().getMemberById("236245509575016451")).getAllowed();
							event.getGuild().getCategoryById("514892197779341315").createTextChannel("Dojo-"+event.getMember().getEffectiveName().split(" ")[0]).setNSFW(false).complete().createPermissionOverride(event.getMember()).setAllow(a).complete();					
						}
					}
					if(event.getMember().getRoles().toString().contains("455944068254662657")) {
						if(!event.getMember().getRoles().toString().contains("Justifié")) {
							//event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById("503678494123753482")).complete();
						}
					}
				}
			}
		}
		
}