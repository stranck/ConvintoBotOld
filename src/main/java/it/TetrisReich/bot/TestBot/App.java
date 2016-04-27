package it.TetrisReich.bot.TestBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.response.SendResponse;

import it.TetrisReich.bot.TestBot.Download;
import it.TetrisReich.bot.TestBot.Chan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import org.json.*;

/**
 * Hello world!
 *
 */
public class App {
	//Gson g = new Gson();
	public static Boolean inLive = false;
	public static String fileCn;
	public static Boolean log = false;
	public static String Convinti;
	public static String name;
	public static Boolean textEsist = true;
	public static String channel;
	public static Boolean crash = false;
	public static String api;
	public static String videoid;
	public static byte liveFinish = 0;
	public static int mesasge_id;
    public static String threadst1;
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
    	//System.out.println(args.length);
    	//Pchat.t.start();
    	if(args.length>=1) log = true; 
    	String token = "197939074:AAG8AeKyywRv-Z0H5TP4kJgat16DSrGtMIQ";
    	TelegramBot bot = TelegramBotAdapter.build(token);
    	if(startup()==false) {logger("Fail to loading file"); return;}
    	bot.sendMessage("@MultychatNews", "bot is again online.");
    	//String id = channel;
    	while(true){
    		String info = getInfo();
    		System.out.println(Convinti + "   " + reader("all"));
    		if(ytupd(info)==true&&!info.equals(reader("all"))){
    			SendResponse sendResponse = bot.sendMessage("@MultychatNews", Chan.chan()+"\n" + name + "\n\n" + info);
    			Message message = sendResponse.message();
    			mesasge_id = message.messageId();
    			writer(Convinti, "all");
    			writer(info, "id");
    			logger("\nyap\n");
    			//The after-live modifier message is still in alpha. I'm waiting for the bot 2.0 update of the library.
    			if(inLive==true) {Clive.t.start(); threadst1 = info;liveFinish = 2;} else {
    				if(liveFinish==1) {Clive.t.stop(); liveFinish = 0;}
    			}
    		} else System.out.println(".");
    		try{
    		    Thread.sleep(5000);
    		} catch(InterruptedException ex){
    		    Thread.currentThread().interrupt();
    		}	
    	}
    	/*JsonObject jsonObject = new JsonParser().parse(Download.dwn()).getAsJsonObject();
    	System.out.println(jsonObject.get("items").getAsString()); */
    }
    
    public static Boolean writer(String text, String path){
    	try {
			File file = new File(path);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(text);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return true;
    }
    public static String reader(String path) throws IOException{
        FileReader fileReader;
        fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader( fileReader );
         
        String line = bufferedReader.readLine();
        return line;
    }
    public static Boolean ytupd(String id) throws IOException{
    	Boolean temp = false;
    	fileCn = "";
    	fileCn = reader("id");
    	if(!id.equals(fileCn)) temp = true; else {logger("\nNop\n");}
    	Convinti = fileCn;
    	System.out.println(temp);
    	return temp;
    }
    public static String getInfo(){
    	JSONObject obj = new JSONObject(Download.dwn(api));
    	JSONArray arr = obj.getJSONArray("items");
    	obj = arr.getJSONObject(0);
    	String result = obj.getJSONObject("id").getString("videoId");
    	name = "";
    	name = obj.getJSONObject("snippet").getString("title");
    	if(obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("live")) inLive = true;
    	videoid = "";
    	videoid = result;
        return "https://youtu.be/" + result;
    }
    public static Boolean newFile(String path){
    	File file = new File(path);
    	boolean blnCreated = false;
        try{
          blnCreated = file.createNewFile();
        }
        catch(IOException ioe){
        	logger("Error while creating a new empty file :" + ioe);
        }
        //System.out.println(blnCreated);
        return blnCreated;
    }
    public static void logger(String testo){
    	if(log==true) System.out.println(testo);
    }
    public static Boolean startup() throws IOException{
    	logger("Starting up");
    	if(url()==false&&idd()==false&&all()==false&&chat()==false) return false;
    	idd();
    	all();
    	chat();
    	File f = new File("text");
    	if(f.exists() && !f.isDirectory()) { 
    		logger("File \"text\" loaded successfully.");
    	} else {
    		logger("File \"text\" not found.");
    		textEsist = false;
    	}
    	logger(reader("chat") + "   " + reader("api"));
    	return true;
    }
    public static Boolean idd(){
    	File f = new File("id");
    	if(f.exists() && !f.isDirectory()) { 
    		writer(getInfo(), "id");
    		logger("File \"id\" loaded successfully.");
    	} else {
    		logger("File \"id\" not found. Creating it.");
    		newFile("id");
    		logger("File \"id\" created and loaded successfully.");
    	}
    	return true;
    }
    public static Boolean all(){
    	File f = new File("all");
    	if(f.exists() && !f.isDirectory()) { 
    		writer("---", "all");
    		logger("File \"all\" loaded successfully.");
    	} else {
    		logger("File \"all\" not found. Creating it.");
    		newFile("all");
    		writer("---", "all");
    		logger("File \"all\" created and loaded successfully.");
    	}
    	return true;
    }
    public static Boolean url() throws IOException{
    	File f = new File("api");
    	if(f.exists() && !f.isDirectory()) { 
    		channel = reader("api");
        	api = (reader("api"));
    		logger("File \"api\" loaded successfully.");
    	} else {
    		logger("Fail to load the \"api\"");
    		return false;
    	}
    	return true;
    }
    public static Boolean chat() throws IOException{
    	File f = new File("chat");
    	if(f.exists() && !f.isDirectory()) { 
    		channel = reader("chat");
    		logger("File \"chat\" loaded successfully.");
    	} else {
    		logger("Fail to load the \"chat\"");
    		return false;
    	}
    	return true;
    }
    public static String threadst = "https://www.googleapis.com/youtube/v3/videos?part=snippet&id="+videoid+"&maxResults=1&key=AIzaSyBNNBKNYa3LrC4fjPyEMXRBTv9vp54v4S8"; 
}