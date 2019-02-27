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


import java.io.IOException;

import java.io.IOException;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class stat extends BotListener {
	public stat(MessageReceivedEvent event) throws IOException {
		message=event.getMessage().getContentDisplay();

		if(message.contains("!stat")) {
			if(event.getMember().getRoles().toString().contains("RP")) {
				Boolean numb=false;
				String xp="";
				id=event.getMember().getUser().getId();
				if(message.contains("@")) {
					String[] tab=message.split(" @");
					
					if(tab.length==2) {
						id=event.getGuild().getMembersByEffectiveName(message.split("!stat @")[1], true).get(0).getUser().getId();
					}else {
						String ids[]=new String[tab.length-1];
						
						
						numb=true;
						for(int i=1;i<tab.length;i++) {
							List<Member> membre=event.getGuild().getMembersByEffectiveName(tab[i], true);
							id=membre.get(0).getUser().getId();
							br = new BufferedReader(new FileReader(lien+id)); 
							xp+=tab[i]+" ";
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();
							br.readLine();

							int choixR=0;
							
							System.out.println(membre.get(0).getRoles().toString());
							for(int i2=0;i2<5;i2++) {
								
								if(membre.get(0).getRoles().toString().contains(nrace[i2])) {
									choixR=i2+1;
								}
							}
							
							int choixC=0;
							
							System.out.println(membre.get(0).getRoles().toString());
							for(int i2=0;i2<nclasse.length;i2++) {
								
								if(membre.get(0).getRoles().toString().contains(nclasse[i2])) {
									choixC=i2+1;
								}
							}
							String v=""+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][0]+classe[choixC][0]);
							xp+="pdv: **"+v+"/"+v+"** ";
							xp+="atk: **"+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][1]+classe[choixC][1])+"** ";
							xp+="def: **"+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][2]+classe[choixC][2])+"** ";
							xp+="tec: **"+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][3]+classe[choixC][3])+"** \n";
							
							br.close();
						}
					}
					
				}
				
				if(!numb) {
					
					Member membre=event.getGuild().getMemberById(id);
					System.out.println(membre.getRoles().toString());
					int choixR=0;
					for(int i2=0;i2<5;i2++) {
						
						if(membre.getRoles().toString().contains(nrace[i2])) {
							choixR=i2+1;
						}
					}
					int choixC=0;
					
					for(int i2=0;i2<nclasse.length;i2++) {
						System.out.println("coucou\n");
						if(membre.getRoles().toString().contains(nclasse[i2])) {
							System.out.println(nclasse[i2]);
							choixC=i2+1;
						}
					}
				
					br = new BufferedReader(new FileReader(lien+id)); 
					xp+=br.readLine()+"\n";
					br.readLine();
					br.readLine();
					xp+=br.readLine()+"\n";
					xp+=br.readLine().replace(":"," : ")+"\n";
					xp+="Points de vie : "+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][0]+classe[choixC][0])+"\n";
					xp+="Attaque : "+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][1]+classe[choixC][1])+"\n";
					xp+="Défense : "+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][2]+classe[choixC][2])+"\n";
					xp+="Technique : "+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][3]+classe[choixC][3])+"\n";
					
				}
				event.getTextChannel().sendMessage(xp).complete();
				
			}
		}
		else if(message.contains("!upgrade")) {
			id=event.getMember().getUser().getId();
			br = new BufferedReader(new FileReader(lien+id)); 
			String xp="";
			String renvoi="";
			xp+=br.readLine()+"\n";
			xp+=br.readLine()+"\n";
			
			String niv=br.readLine().split(":")[1];
			xp+="niveau:"+niv+"\n";
			
			xp+=br.readLine()+"\n";
			int val=Integer.parseInt(br.readLine().split(":")[1]);
			int pdv=Integer.parseInt(br.readLine().split(":")[1]);
			int atq=Integer.parseInt(br.readLine().split(":")[1]);
			int def=Integer.parseInt(br.readLine().split(":")[1]);
			int teq=Integer.parseInt(br.readLine().split(":")[1]);
			int vn=6;
				int max;
				//int pos[]= {2,1,3,2,3,3,5,5,7,7,10};
				
				int tab1[] = {45,50,55};
				int tab2[] = {60,65,70};
				int tab3[] = {75,65,70};
				//int tab[]= {45,50,55,60,65,70,75,80,85,90,95,100};
				int pos2[]= {1,1,1,2,2,2,3,4,5,6,7,9};
				int tab[]= {15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100};
				int pos[]= {1,1,2,2,3,5,3,3,4,6,4,4,5,7,6,6,7,7};
				
				
				for(int i=0;i<tab.length;i++) {
					if(Integer.parseInt(niv)>=tab[i]) {
						vn+=pos[i];
					}
				}
				
			//for(int i=0;i<tab1.length;i++) {
				//if(Integer.parseInt(niv)>=tab1[i]){
					//vn=vn+1;
				//}
			//}
			
			//for(int i=0;i<tab2.length;i++) {
				//if(Integer.parseInt(niv)>=tab2[i]){
					//vn=vn+2;
				//}
			//}
			
			if(message.contains("pdv") || message.contains("point de vie") || message.contains("pv") ) {
				
				if(vn%4!=0) {max=200+50*(vn-5)+50*(vn/2+vn/4);}
				else 		{max=200+50*(vn-5)+50*(vn/2+vn/4)+50;}
				//
				if(val==0) {
					renvoi="Aucun point à distribuer";
				}else if (max<pdv+50) {
						renvoi="Limite actuelle atteinte pour les points de vie";
					}else {
						pdv=pdv+50;
						val=val-1;
						renvoi="augmentation des points de vie réussite";
				}
				
			}
			
			if(message.contains("atq") || message.contains("attaque")|| message.contains("atk") || message.contains("att"))  {
				if(vn%2!=0) 	{max=20+5*(vn-5)+5*(vn/2+vn/4);}
				else 			{max=20+5*(vn-5)+5*(vn/2+vn/4)+5;}
				

				if(val==0) {
					renvoi="Aucun point à distribuer";
				}else if (max<atq+5) {
						renvoi="Limite actuelle atteinte pour l'attaque";
					}else {
						atq=atq+5;
						val=val-1;
						renvoi="augmentation de l'attaque réussite";
				}
				
			}
			
			if(message.contains("def") || message.contains("défense") || message.contains("defense")) {
				if(vn%2!=0) 	{max=20+5*(vn-5)+5*(vn/2+vn/4);}
				else 			{max=20+5*(vn-5)+5*(vn/2+vn/4)+5;}
				if(val==0) {
					renvoi="Aucun point à distribuer";
				}else if (max<def+5) {
						renvoi="Limite actuelle atteinte pour la défense";
						
					}else {
						def=def+5;
						val=val-1;
						renvoi="augmentation de la défense réussite";
				}
				
			}
			
			if(message.contains("teq") || message.contains("tec")|| message.contains("tek")|| message.contains("teck")|| message.contains("technique")|| message.contains("tech")) {

				if(vn%2!=0) {max=(vn-5)+vn/2+vn/4;}
				else 		{max=(vn-5)+vn/2+vn/4+1;}
				
				if(val==0) {
					renvoi="Aucun point à distribuer";
				}else if (max<teq+1) {
						renvoi="Limite actuelle atteinte pour la technique";
					}else {
						teq=teq+1;
						val=val-1;
						renvoi="augmentation de la technique réussite";
				}
				
			}
			xp+="Points à distribuer:"+val+"\n";
			xp+="Points de vie:"+pdv+"\n";
			xp+="Attaque:"+atq+"\n";
			xp+="défense:"+def+"\n";
			xp+="Technique:"+teq+"\n";
			String line="";
			while((line=br.readLine()) != null) {xp+=line+"\n";}
			br.close();
			writer = new BufferedWriter(new FileWriter(new File(lien+id)));
			writer.write(xp);
			writer.close();
			
			event.getTextChannel().sendMessage(renvoi).complete();
			
		}
}
}