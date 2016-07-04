package it.TetrisReich.bot.TestBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import it.TetrisReich.bot.TestBot.Download;
import it.TetrisReich.bot.TestBot.Chan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import org.json.*;

public class App {

	public static String token;
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
    public static String threadst2 = "";
    public static Boolean link = false;
    public static Boolean comp = false;
    public static Boolean stat = false;
    public static String liveEnd;
    public static int lastId = 0;
    public static boolean cristoEVenuto = false;
    public static int lf = 0;
    public static int vf = 0;
    public static String key;
    public static String threadst(){
    	return "https://www.googleapis.com/youtube/v3/videos?part=snippet&id="+videoid+"&maxResults=1&key="+key; 
    }
	public static void main(String[] args) throws IOException {
    		for (String s: args) {
        	if(args.length>=1) log = true; 
        	if(s.equals("-l")) link = true;
        	if(s.equals("-c")) comp = true;
        	if(s.equals("-s")) stat = true;
        	if(s.equals("-a")) {
        		stat = true;
        		comp = true;
        		link = true;
        	}
    	}
    	if(Startup.startup()==false) {logger("Fail to loading file"); return;}
    	TelegramBot bot = TelegramBotAdapter.build(token);
    	bot.execute(new SendMessage("@MultychatNews", "*bot is again online.*\n_[version: pre1.0]_")
    			.parseMode(ParseMode.Markdown));
    	long nTotalCheck = 0;
    	long nVCheck = 0;
    	long nLCheck = 0;
    	long nVUpdate = 0;
    	long nLUpdate = 0;
    	while(true){
    		nTotalCheck++;
    		if(stat==true){
    			logger("\nTotal number of check: " + nTotalCheck);
    			logger("Total number of check of video update: " + nVCheck);
    			logger("Total number of check of live update: " + nLCheck);
    			logger("Total number of video update: " + nVUpdate);
    			logger("Total number of live update: " + nLUpdate + "\n");
    		}
    		String info = getInfo();
    		logger("Actual id: " + Convinti + "\nId saved: " + reader("all"));
    		if(liveFinish<3){
    			nVCheck++;
    			loggerL("\nChecking for new video: ");
    			if(ytupd(info)==true&&!info.equals(reader("all"))){
    				nVUpdate++;
    				channel = "@MultychatNews";
    				String mText = Chan.chan()+"\n[" + name + "](" + info+ ")";
    				SendResponse sendResponse = bot.execute(new SendMessage(channel, mText)
    						.parseMode(ParseMode.Markdown));
    				Message message = sendResponse.message();
    				eLast(false);
    				bot.execute(new EditMessageText(channel, lastId, eLast(true))
    						.parseMode(ParseMode.Markdown)
    						.disableWebPagePreview(true));
    				writer(mText + ";" + message.messageId(), "Last");
    				writer(Convinti, "all");
    				writer(info, "id");
    				logger("true\n");
    				if(inLive==true) {
    					logger("Live founded!");
    					threadst1 = threadst();
    					liveFinish = 2;
    					mesasge_id = message.messageId();
    					nVUpdate++;
    					inLive = false;
    					threadst2 = info;
    					if(Clive.checkUpcoming()){bot.execute(new EditMessageText(channel, mesasge_id,
    							"[Live programmata]\n[" + name + "](" + info+ ")")
    							.parseMode(ParseMode.Markdown));}
    				}
    			} else {if(comp==true)logger("false");}
    			if(liveFinish==1) {
					bot.execute(new EditMessageText(channel, mesasge_id, liveEnd)
							.parseMode(ParseMode.Markdown)
							.disableWebPagePreview(true));
					liveFinish = 0;
					}
    			if(liveFinish>0) liveFinish = 3;
    			if(cristoEVenuto){
    				inLive = true;
    				bot.execute(new EditMessageText(channel, mesasge_id,
							Chan.chan()+"\n[" + name + "](" + threadst2 + ")")
							.parseMode(ParseMode.Markdown));
    				inLive = false;
    				cristoEVenuto = false;
    			}
    		} else {nLCheck++; Clive.checkInLive();}
    		try{
    		    Thread.sleep(5000);
    		} catch(InterruptedException ex){
    		    Thread.currentThread().interrupt();
    		}	
    	}
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
        bufferedReader.close();
        return line;
    }
    public static Boolean ytupd(String id) throws IOException{
    	loggerL("Checking for an id change:");
    	Boolean temp = false;
    	fileCn = "";
    	fileCn = reader("id");
    	if(!id.equals(fileCn)) temp = true;
    	Convinti = fileCn;
    	if(comp==true) logger(" " + temp);
    	return temp;
    }
    public static String getInfo(){
    	JSONObject obj = new JSONObject(Download.dwn(api));
    	JSONArray arr = obj.getJSONArray("items");
    	obj = arr.getJSONObject(0);
    	String result = obj.getJSONObject("id").getString("videoId");
    	name = "";
    	name = obj.getJSONObject("snippet").getString("title");
    	if(
    			obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("live")||
    			obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("upcoming")
    			) inLive = true;
    	videoid = "";
    	videoid = result;
        return "https://youtu.be/" + result;
    }
    public static String eLast(Boolean ft) throws IOException{
    	StringTokenizer st = new StringTokenizer(allLine("Last"), ";");
    	String ret = st.nextToken();
    	if(ft==false){
    		lastId = 0;
    		lastId = Integer.parseInt(st.nextToken());
    	} else {logger("Changing last sended message using: " + allLine("Last"));}
    	return ret;
    }
    public static String allLine(String name) throws FileNotFoundException, IOException{
    	String line;
		String ret = "";
    	try (
    	    InputStream fis = new FileInputStream(name);
    	    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
    	    BufferedReader br = new BufferedReader(isr);
    	) {
    		int first = 0;
    	    while ((line = br.readLine()) != null) {
    	    	if(first<1){first++;}else{ret = ret + line;}
    	    }
    	}
    	return ret;
    }
    public static void newFile(String path){
    	File file = new File(path);
        try{
          file.createNewFile();
        }
        catch(IOException ioe){
        	logger("Error while creating a new empty file :" + ioe);
        }
    }
    public static void logger(String testo){
    	if(log==true) System.out.println(testo);
    }
    public static void loggerL(String testo){
    	if(log==true) System.out.print(testo);
    }
}
