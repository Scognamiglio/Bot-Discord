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
import java.util.List;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

public class Gestion extends BotListener {
	
public Gestion(MessageReceivedEvent event) throws IOException {
	message=event.getMessage().getContentDisplay();

	
	
	if(message.contains("!level")) {
		Boolean autre=false;
		String personne;
		String[] truc;
		id=event.getMember().getUser().getId();
		if(message.contains("@")) {
			String abc=message.split("!level @")[1];
			if(abc.contains("/")) {
				autre=true;
				abc=abc.split("/")[0];
			}
			id=event.getGuild().getMembersByEffectiveName(abc, true).get(0).getUser().getId();
			
		}
		System.out.println(lien+id+"\n");
		br = new BufferedReader(new FileReader(lien+id)); 
		br.readLine();
		String xp=br.readLine().split("experience:")[1];
		int niveau=Integer.parseInt(br.readLine().split("niveau:")[1]);
		int total=niveau*niveau*niveau/4;
		truc=message.split("/");
		if(autre) {
			personne=message.split("!level @")[1].split("/")[0];
		}else {
			personne=event.getMember().getEffectiveName();
		}
		
		if(truc.length==1) {
			event.getTextChannel().sendMessage("tu es niveau "+niveau+" avec "+xp+" points d'experience et il t'en faut "+total).complete();
		}else {
			int min=Integer.parseInt(truc[1]);
			int fin=total/(niveau*10)*min;
			
			event.getTextChannel().sendMessage("!xpadd @"+personne+" xp"+fin).complete();
			event.getGuild().getTextChannelById("504414137917505537").sendMessage("!xpadd @"+personne+" xp"+fin).complete();
		}
		
		
	}
	///////////////////////////////////////////////////////
	else if(message.contains("!xpadd")) {
		if(event.getMember().getRoles().toString().contains("MJ")) {
			String renvoi="";
			String tab=message.split("!xpadd @")[1];
			String personne=tab.split(" xp")[0];
			Boolean up=false;
			String tableau[]=tab.split(" xp")[1].split(" ");
			int xp;
			if(tableau.length==1) {
				xp=Integer.parseInt(tableau[0]);
			}else {
				xp=Integer.parseInt(tableau[0])*Integer.parseInt(tableau[1])/100;
			}
			
			String id=event.getGuild().getMembersByEffectiveName(personne, true).get(0).getUser().getId();
			br = new BufferedReader(new FileReader(lien+id));
			renvoi+=br.readLine()+"\n";
			int b=Integer.parseInt(br.readLine().split("experience:")[1]);
			int niv=Integer.parseInt(br.readLine().split("niveau:")[1]);
			if(niv<40) {
				xp=xp*2;
			}
			if(niv>70) {
				xp=xp/2;
			}
			b=b+xp;
			boolean suite=true;
			while(suite) {
				suite=false;
				if(b>(niv*niv*niv/4)) {
					b=b-niv*niv*niv/4;
					niv+=1;
					suite=true;
					
						if((niv%5)==0) {up=true;}
					}
					
					
					
				
			}
			renvoi+="experience:"+b+"\n"+"niveau:"+niv+"\n";
			String line="";
			while((line=br.readLine()) != null) {renvoi+=line+"\n";}
		
			writer = new BufferedWriter(new FileWriter(new File(lien+id)));
			writer.write(renvoi);
			writer.close();
			
			
			
			event.getTextChannel().sendMessage("xp: "+b+" niv:"+niv).complete();
			if(up) {
				int valeurs=1;
				//int pos[]= {2,1,3,2,3,3,5,5,7,7,10};
				int pos[]= {1,1,2,2,3,5,3,3,4,6,4,4,5,7,6,6,7,7};
				
				
				for(int i=0;i<pos.length;i++) {
					if(niv>14+i*5 && niv<14+(i+1)*5) {
						valeurs=pos[i];
						}
					}niveau(event,id,niv,valeurs);
			}
		}else {
			event.getTextChannel().sendMessage("Vous n'avez pas les droits de le faire, que dis-je, vous n'avez aucun droit. Bip bip **est déjà loin**").complete();
		}}
	///////////////////////////////////////////////////////
	else if(message.contains("!stop")) {
		if(event.getMember().getRoles().toString().contains("Admin")) {
			System.exit(0);
			}else {
				event.getTextChannel().sendMessage("Désolé, mais j'ai plus de pouvoir que toi, me cherche pas, ou c'est toi que je vais éteindre").complete();
			} 
		}
	else if(message.contains("!add-event")) {
		if(event.getMember().getRoles().toString().contains("RP")) {
			event.getGuild().getController().addSingleRoleToMember(event.getMember(), event.getGuild().getRoleById("478669996072108055")).complete();
		}
	}
	else if(message.contains("!end-event")) {
		
		if(event.getMember().getRoles().toString().contains("MJ")) {
			List<Member> members=event.getGuild().getMembersWithRoles(event.getGuild().getRoleById("478669996072108055"));
			for(int i=0;i<members.size();i++) {
				event.getGuild().getController().removeSingleRoleFromMember(members.get(i), event.getGuild().getRoleById("478669996072108055")).complete();
				}
			}
	}
	
	
}
public void niveau(MessageReceivedEvent event,String id,int niveau, int ptn) throws IOException {
	br = new BufferedReader(new FileReader(lien+id));
	String line="";
	String renvoi="";

	while((line=br.readLine()) != null) {renvoi+=line+"\n";}
	String[] tab=renvoi.split("\n");
	for(int i=0;i<ptn;i++) {
		int valeur=Integer.parseInt(tab[4].split(":")[1]);
		tab[4]="Points à distribuer:"+(valeur+1);
		
		valeur=Integer.parseInt(tab[5].split(":")[1]);
		tab[5]="Points de vie:"+(valeur+50);
		
		valeur=Integer.parseInt(tab[6].split(":")[1]);
		tab[6]="Attaque:"+(valeur+5);
		
		valeur=Integer.parseInt(tab[7].split(":")[1]);
		tab[7]="défense:"+(valeur+5);
		
		valeur=Integer.parseInt(tab[8].split(":")[1]);
		tab[8]="Technique:"+(valeur+1);
	}
	renvoi="";
	for(int i=0;i<tab.length;i++) {
		renvoi+=tab[i]+"\n";
	}
	while((line=br.readLine()) != null) {renvoi+=line+"\n";}
	writer = new BufferedWriter(new FileWriter(new File(lien+id)));
	writer.write(renvoi);
	writer.close();
	
	String[] a={"456269567535349791","456270200451366914","456270295951736832","477642341205934080","483769941355528213","485849180393439302","503466792937717760"};
	
	for(int i=0;i<a.length;i++) {
		if(niveau>=(i+2)*10 && niveau<(i+3)*10 ) {
			if(!event.getGuild().getMemberById(id).getRoles().toString().contains(a[i+1])) {
				
				event.getGuild().getController().addSingleRoleToMember(event.getGuild().getMemberById(id), event.getGuild().getRoleById(a[i+1])).complete();
				event.getGuild().getController().removeSingleRoleFromMember(event.getGuild().getMemberById(id), event.getGuild().getRoleById(a[i])).complete();
			}
		}
	}

}
}
