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

public class Donjon extends BotListener {
	public Donjon(MessageReceivedEvent event) throws IOException {
		
		message=event.getMessage().getContentDisplay();
		if(message.contains("!donjon")) {
			String[]z=message.split(" ");
			String ident="478669996072108055";
			String vie="vie";
			if(z.length!=1) {
				
				if(z[1].equals("1")) {
					vie="vie1";
					ident="482293288142110721";
				}
				if(z[1].equals("2")) {
					vie="vie2";
					ident="482293260027428904";
				}
			}
			String xp="";
			String msg="-------------------------------\n`\n```XML";
			List<Member> t=event.getGuild().getMembersWithRoles(event.getGuild().getRoleById(ident));
			String pv="";
			String renvoi="";
			for(int i=0;i<t.size();i++) {
				Member usr=t.get(i);
				
				
				br = new BufferedReader(new FileReader(lien+usr.getUser().getId())); 
				xp+=usr.getEffectiveName()+" ";
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				
				int choixR=0;
				for(int i2=0;i2<5;i2++) {
					
					if(usr.getRoles().toString().contains(nrace[i2])) {
						choixR=i2+1;
					}
				}
				int choixC=0;
				for(int i2=0;i2<nclasse.length;i2++) {
					
					if(usr.getRoles().toString().contains(nclasse[i2])) {
						choixC=i2+1;
					}
				}
				
				
				String v=""+(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][0]+classe[choixC][0]);
				pv+=usr.getEffectiveName()+":"+v+"/"+v+"\n";
				xp+="pdv: **"+v+"/"+v+"** ";
				int atk=(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][1]+classe[choixC][1]);
				xp+="atk: **"+atk+"** \n";
				int def=(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][2]+classe[choixC][2]);
				int teq=(Integer.parseInt(br.readLine().split(":")[1])+race[choixR][3]+classe[choixC][3]);
				br.close();
				renvoi+="<"+usr.getEffectiveName()+":"+v+":"+atk+":"+def+":"+teq+"\n";
				msg+="\n<"+usr.getEffectiveName().split(" ")[0].split("/")[0]+" atk:"+atk+">\n";
			}
			msg+="```\n`";
			
			event.getTextChannel().sendMessage(xp+msg).complete();
			writer = new BufferedWriter(new FileWriter(new File(lien+vie)));
			writer.write(pv);
			writer.close();
			writer = new BufferedWriter(new FileWriter(new File(lien+"stat")));
			writer.write(renvoi);
			writer.close();
		}
		
		else if(message.contains("```xml\nboss")) {
			String act[]=message.split("```xml\nboss")[1].split("```")[0].split("<");
			String renvoi="";
			String perso="";
			int deg;
			br = new BufferedReader(new FileReader(lien+"vie"));
			String line;
			String line2="";
			String truc="";
			while ((line= br.readLine()) != null) {
				line2+=line+"\n";
			}
			
			br.close();
			String lines[]=line2.split("\n");
			for(int i=1;i<act.length;i++) {
				System.out.println(act[i]);
				perso=act[i].split(" ")[0];
				deg=Integer.parseInt(act[i].split(" ")[1].split(">")[0]);
				
				for(int i2=0;i2<lines.length;i2++) {
					if(lines[i2].contains(perso)) {
						int vie=(Integer.parseInt(lines[i2].split(":")[1].split("/")[0])-deg);
						lines[i2]=lines[i2].split(":")[0]+":"+vie+"/"+lines[i2].split("/")[1];
						truc+="Le joueur "+perso+" à encore "+vie+" pv"+"\n";
					}
				}
				
				
				
			}

			writer = new BufferedWriter(new FileWriter(new File(lien+"vie")));
			line="";
			for(int i=0;i<lines.length;i++) {
				line+=lines[i]+"\n";
			}
			writer.write(line);
			writer.close();
			event.getTextChannel().sendMessage(truc).complete();
		}
		else if(message.contains("!pdvadd")) {
			
			br = new BufferedReader(new FileReader(lien+"vie"));
			String line;
			String line2="";
			while ((line= br.readLine()) != null) {line2+=line+"\n";}
			br.close();
			writer = new BufferedWriter(new FileWriter(new File(lien+"vie")));
			String info=message.split("!pdvadd ")[1];
			writer.write(line2+info.split(" ")[0]+":"+info.split(" ")[1]+"/"+info.split(" ")[1]);
			writer.close();
		}
		
		else if(message.contains("```xml\njoueur")) {
			String act[]=message.split("```xml\njoueur")[1].split("```")[0].split("<");
			br = new BufferedReader(new FileReader(lien+"vie"));
			String line;
			String line2="";
			while ((line= br.readLine()) != null) {line2+=line+"\n";}
			
			String tab[]=line2.split("\n");
			br.close();
			line="";
			int deg=0;
			for(int i=1;i<act.length;i++) {
				br = new BufferedReader(new FileReader(lien+"stat"));
				while ((line= br.readLine()) != null) {
					if(line.contains(act[i].split(" ")[0])) {
						deg=Integer.parseInt(line.split(":")[2])*Integer.parseInt(act[i].split(" ")[1])/100;
						}
					}
				for(int i2=0;i2<tab.length;i2++) {
					
					if(tab[i2].contains(act[i].split(" ")[2].split(">")[0])) {
						deg=Integer.parseInt(tab[i2].split(":")[1].split("/")[0])-deg;
						if(deg<0) {deg=0;}
						tab[i2]=tab[i2].split(":")[0]+":"+deg+"/"+tab[i2].split(":")[1].split("/")[1];
						}
				}

			}
			line="";
			for(int i=0;i<tab.length;i++) {line+=tab[i]+"\n";}
			writer = new BufferedWriter(new FileWriter(new File(lien+"vie")));
			writer.write(line);
			writer.close();
		}
		else if(message.contains("!pdv")) {
			
			String ident="478669996072108055";
			String vie="vie";

				if(message.contains(("pdv1"))) {
					vie="vie1";
					ident="482293288142110721";
				}
				if(message.contains(("pdv2"))) {
					vie="vie2";
					ident="482293260027428904";
				}
			
			
			if(event.getMember().getRoles().toString().contains(ident)) {
				br = new BufferedReader(new FileReader(lien+vie));
				String a="";
				Boolean test=true;
				
				String renvoi="";
				
					while(test) {
						
						a=br.readLine();
						
						if(a==null) {
							test=false;
						}else {
						if(message.split(" a").length==1) {
							if(a.contains(event.getMember().getEffectiveName())) {
								String b=a.split(":")[1];
								renvoi=event.getMember().getEffectiveName()+" il te reste encore "+b.split("/")[0]+" pv sur "+b.split("/")[1];
								}
							}else {
								renvoi+=a+"\n";
								}
				}}event.getTextChannel().sendMessage(renvoi).complete();

		}
		}
		else if(message.contains("!degat")) {
			
			String ident="478669996072108055";
			String vie="vie";
			if(message.contains(("degat1"))) {
				vie="vie1";
				ident="482293288142110721";
			}
			if(message.contains(("degat2"))) {
				vie="vie2";
				ident="482293260027428904";
			}
			if(event.getMember().getRoles().toString().contains("455943719124729858")) {
				String[] degs=message.split(" ");
				String renvoi="";
				String a="";
				String nom;
				int pv,pv_m;
				int val;
				if (event.getGuild().getMembersWithRoles(event.getGuild().getRoleById(ident)).size()!=degs.length-1) {
					
					event.getTextChannel().sendMessage("Problème dans le nombre de valeur... **soupir**\n C'est si dur que ça de compter ? :3").complete();
				}else {
					br = new BufferedReader(new FileReader(lien+vie));
					
					for(int i=1;i<degs.length;i++) {
						a=br.readLine();
						nom=a.split(":")[0];
						pv=Integer.parseInt(a.split(":")[1].split("/")[0]);
						pv_m=Integer.parseInt(a.split(":")[1].split("/")[1]);
						
						val=pv-Integer.parseInt(degs[i]);
						if(val<0) {
							val=0;
						}
						if(val>pv_m) {
							val=pv_m;
						}
						renvoi+=nom+":"+val+"/"+pv_m+"\n";
					}
					br.close();
					writer = new BufferedWriter(new FileWriter(new File(lien+vie)));
					writer.write(renvoi);
					writer.close();
				}
				
			}
			
		}
	}
}
