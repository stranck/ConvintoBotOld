package it.TetrisReich.bot.TestBot;

import java.io.File;
import java.io.IOException;
/*import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;*/
import java.net.ConnectException;


public class Startup {
	 public static Boolean startup() throws IOException {
	    	App.logger("Starting up");
	    	if(apiKey()&&url()==false&&idd()==false&&all()==false
	    			&&chat()==false&&cLast()&&apiToken()&&kronos()&&admin()) return false;
	    	idd();
	    	cLast();
	    	all();
	    	apiToken();
	    	chat();
	    	apiKey();
	    	kronos();
	    	admin();
	    	//dir();
	    	if(App.skipDefaultDirectory==false) App.dir = "";
	    	App.logger("\nTelegram channel: " + App.channel + "\n Telegram api: " + App.api);
	    	File f = new File("text");
		    if(f.exists() && !f.isDirectory()) { 
		    	App.logger("File \"text\" loaded successfully.");
		    } else {
		    	App.logger("File \"text\" not found.");
		    	App.textEsist = false;
		    }   
	    	if(App.textEsist) Chan.chanSP();
	    	//logger(reader("chat") + "   " + reader("api"));
	    	App.logger("TextEsist: " + App.textEsist);
	    	return true;
	    }
	    public static Boolean idd(){
	    	File f = new File("id");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.writer(App.getInfo(0), "id");
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
	    		App.writer(App.getInfo(1), "all");
	    		App.logger("File \"all\" loaded successfully.");
	    	} else {
	    		App.logger("File \"all\" not found. Creating it.");
	    		App.newFile("all");
	    		App.writer(App.getInfo(1), "all");
	    		App.logger("File \"all\" created and loaded successfully.");
	    	} 	
	    	return true;
	    }
	    public static Boolean url() throws IOException{
	    	File f = new File("channelID");
	    	if(f.exists() && !f.isDirectory()) { 
	    		//channel = reader("api");
	    		App.api = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId="+
	        		(App.reader("channelID"))+
	    			"&order=date&key=" + App.key + "&maxResults=";
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
	    public static Boolean kronos(){
	    	File f = new File("kronos");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.logger("File \"kronos\" loaded successfully.");
	    	} else {
	    		App.logger("File \"kronos\" not found. Creating it.");
	    		App.newFile("kronos");
	    		App.logger("File \"kronos\" created and loaded successfully.");
	    	}
	    	return true;
	    }
	    public static boolean admin(){
	    	File f = new File("admin");
	    	if(f.exists() && !f.isDirectory()) { 
	    		App.logger("File \"admin\" loaded successfully.");
	    	} else {
	    		App.logger("File \"admin\" not found. Creating it.");
	    		App.newFile("admin");
	    		App.logger("File \"admin\" created and loaded successfully.");
	    	}
	    	return true;
	    }
	    public static boolean check() throws ConnectException {
	    	App.loggerL("Check if this chat is allowed: ");
	    	App.secret = true;
	    	String[] result = Download.dwn("http://stranckutilies.altervista.org/allowed").split(";");
	    	App.secret = false;
	    	for (int x=0; x<result.length; x++) if(result[x].equalsIgnoreCase(App.channel)){
	    		App.logger("TRUE");
	    		return false;
	    	}
    		App.logger("FALSE");
	    	return true;
	    }
	    /*public static boolean dir(){
	    	URL s;
	    	App.loggerL("Loading default directory... ");
	    	try {
				s = Startup.class.getProtectionDomain().getCodeSource().getLocation();
		    	//App.dir = URLDecoder.decode(s, "UTF-8");
				System.out.println(s);
			} catch (NullPointerException e)
	    		{e.printStackTrace(); return false;}
	    	App.logger(App.dir);
	    	return true;
	    }*/
}
