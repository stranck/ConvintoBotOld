package it.TetrisReich.bot.TestBot;

import java.io.File;
import java.io.IOException;

public class Startup {
	 public static Boolean startup() throws IOException{
	    	App.logger("Starting up");
	    	if(apiKey()&&url()==false&&idd()==false&&all()==false&&chat()==false&&cLast()&&apiToken()) return false;
	    	idd();
	    	cLast();
	    	all();
	    	apiToken();
	    	chat();
	    	apiKey();
	    	App.logger("\nTelegram channel: " + App.channel + "\n Telegram api: " + App.api);
	    	Chan.chanSP();
	    	File f = new File("text");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.logger("File \"text\" loaded successfully.");
	    	} else {
	    		App.logger("File \"text\" not found.");
	    		App.textEsist = false;
	    	}
	    	App.logger("TextEsist: " + App.textEsist);
	    	return true;
	    }
	    public static Boolean idd(){
	    	File f = new File("id");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.writer(App.getInfo(), "id");
	    		App.logger("File \"id\" loaded successfully.");
	    	} else {
	    		App.logger("File \"id\" not found. Creating it.");
	    		App.newFile("id");
	    		App.logger("File \"id\" created and loaded successfully.");
	    	}
	    	return true;
	    }
	    public static Boolean all(){
	    	File f = new File("all");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.writer("---", "all");
	    		App.logger("File \"all\" loaded successfully.");
	    	} else {
	    		App.logger("File \"all\" not found. Creating it.");
	    		App.newFile("all");
	    		App.writer("---", "all");
	    		App.logger("File \"all\" created and loaded successfully.");
	    	} 	
	    	return true;
	    }
	    public static Boolean url() throws IOException{
	    	File f = new File("channelID");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.api = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId="+
	        		(App.reader("channelID"))+
	    			"&maxResults=1&order=date&key=" + App.key;
	    		App.logger("File \"channelID\" loaded successfully.");
	    	} else {
	    		App.logger("Fail to load the file \"channelID\"");
	    		return false;
	    	}
	    	return true;
	    }
	    public static Boolean chat() throws IOException{
	    	File f = new File("chat");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.channel = App.reader("chat");
	    		App.logger("File \"chat\" loaded successfully.");
	    	} else {
	    		App.logger("Fail to load the file \"chat\"");
	    		return false;
	    	}
	    	return true;
	    }
	    public static Boolean cLast(){
	    	File f = new File("Last");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.logger("File \"Last\" loaded successfully.");
	    	} else {
	    		App.logger("File \"Last\" not found. Creating it.");
	    		App.newFile("Last");
	    		App.writer("---;123", "Last");
	    		App.logger("File \"Last\" created and loaded successfully.");
	    	} 	
	    	return true;
	    }
	    public static Boolean apiToken() throws IOException{
	    	File f = new File("token");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.token = App.reader("token");
	    		App.logger("File \"token\" loaded successfully.");
	    	} else {
	    		App.logger("Fail to load the file \"token\"");
	    		return false;
	    	}
	    	return true;
	    }
	    public static Boolean apiKey() throws IOException{
	    	File f = new File("key");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.key = App.reader("key");
	    		App.logger("File \"key\" loaded successfully.");
	    	} else {
	    		App.logger("Fail to load the file \"key\"");
	    		return false;
	    	}
	    	return true;
	    }
}
