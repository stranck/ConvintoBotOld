package it.TetrisReich.bot.TestBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;

import it.TetrisReich.bot.TestBot.Download;
import it.TetrisReich.bot.TestBot.MyRunnable;
import it.TetrisReich.bot.TestBot.Chan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.json.*;

/**
 * Hello world!
 *
 */
public class App {
	//Gson g = new Gson();
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
    public static boolean secret = false;
    public static long nTotalCheck = 0;
    public static long nVCheck = 0;
    public static long nLCheck = 0;
	public static long nVUpdate = 0;
	public static long nLUpdate = 0;
	public static boolean s = false;
	public static int altervistamerda = 0;
    public static final long startTime = System.currentTimeMillis();
    public static boolean tempLine = true;
    public static boolean mainT = false;
    public static boolean secondT = false;
    public static String dir = "";
    public static boolean skipDefaultDirectory = false;
    public static boolean skipOnlineCheck = false;
    public static String[] ag;
    public static final String version = "pre1.0.02092016";
    public static String threadst(){
    	return "https://www.googleapis.com/youtube/v3/videos?part=snippet&id="+videoid+"&maxResults=1&key="+key; 
    }
    public static boolean checKill() throws ConnectException{
    	secret = true;
    	String data = Download.dwn("http://stranckutilies.altervista.org/kill");
    	if(data.equalsIgnoreCase(channel)){System.out.println("admin is turning of this bot. bye bye :D");
    		return true;}
    	secret = false;
    	crash = true;
    	return false;
    } //pre1.0.04082016
    /*public static void threadst3(String txt){
    	threadst2 = txt;
    }*/
    //@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
    	//System.out.println(allLine("Last"));
    		for (String s: args) {
        	if(args.length>=1) log = true; 	
        	if(s.equals("-l")) link = true;
        	if(s.equals("-c")) comp = true;
        	if(s.equals("-s")) stat = true;
        	if(s.equals("-d")) skipDefaultDirectory = true;
        	if(s.equals("-ml"))moveLog();
        	if(s.equals("-o")) skipOnlineCheck = true;
        	//if(s.equals("-u")) linux = true;
        	if(s.equals("-a")) {
        		stat = true;
        		comp = true;
        		link = true;
        	}
    	}
    		ag = args;
    	//System.out.println(args.length);
    	//Pchat.t.start();

    	//String token = "197939074:AAG8AeKyywRv-Z0H5TP4kJgat16DSrGtMIQ";
    	if(Startup.startup()==false) {logger("Fail to loading file"); return;}
    	//System.out.println(token);
    	TelegramBot bot = TelegramBotAdapter.build(token);
    	//System.out.println("teeeeeeaaaaaaaAAAAAAAAAAAA time");
    	bot.execute(new SendMessage("-1001063772015" , "*bot is again online on "+channel+".*\n"
    			+ "_[version: "+version+"]_\n")
    			.parseMode(ParseMode.Markdown).disableWebPagePreview(true));
    	/*if(Startup.check()){
    		bot.execute(new SendMessage(channel, "*This bot is not allowed in this chat.*\n"
    				+ "Please, pm me on my [youtube channel](https://www.youtube.com/user/STRANCKluchetto/about).")
    				.parseMode(ParseMode.Markdown).disableWebPagePreview(true));
    		return;
    	}*/
    	/*GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates().limit(0).offset(0).timeout(0));
    	List<Update> updates = updatesResponse.updates();
    	*/
    	//bot.sendMessage("@MultychatNews", "bot is again online.");
    	//String id = channel;
    	MyRunnable myRunnable = new MyRunnable(10);
        Thread t = new Thread(myRunnable);
        t.start();
        logger("VF: "+vf+" LF: "+lf);
        byte tesThread = 0;
    	while(true){
    		mainT = true;
    		try{
    		nTotalCheck++;
    		altervistamerda++;	
    		if(tesThread==4){if(secondT==false){
    					System.out.println("Detect crash of second thread. Restarting the bot.");
    					return; 
    				}else {tesThread = 0; secondT = false;}}
    		//System.out.println("ARGH! "+altervistamerda);
    		//if(true) return;
    		if(skipOnlineCheck) altervistamerda = 0;
    		if(altervistamerda==60) {if(upFile()){
    			System.out.println("Updating file");
    	    	bot.execute(new SendMessage("-1001063772015" , "Updating file\n" +
    	    			Download.dwn("http://stranckutilies.altervista.org/editFile")
    	    			+ "\n_[version: "+version+"]_\n")
    	    			.parseMode(ParseMode.Markdown).disableWebPagePreview(true));
    		}
			if(checKill()||Jar.update()) {
    			String text;
    			if(crash==false) text = channel + "> Terminated by admin."; else text = channel + "> Updating bot.";
    	    	bot.execute(new SendMessage("-1001063772015" , text
    	    			+ "\n_[version: "+version+"]_\n")
    	    			.parseMode(ParseMode.Markdown).disableWebPagePreview(true));
    			s = true;
    			return;
    		} else altervistamerda = 0;}
    		//if(Update.update()) main(args);
    		if(stat==true){
    			logger("\nTotal number of check: " + nTotalCheck);
    			logger("Total number of check of video update: " + nVCheck);
    			logger("Total number of check of live update: " + nLCheck);
    			logger("Total number of video update: " + nVUpdate);
    			logger("Total number of live update: " + nLUpdate + "\n");
    		} if(s) return;
    		String info = getInfo(0);
    		if(info!=null){
    		logger("Actual id: " + Convinti + "\nId saved: " + reader("all"));
    		if(liveFinish<3){
    			//System.out.println("min");
    			nVCheck++;
    			loggerL("\nChecking for new video: ");
    			if(ytupd(info)==true&&!info.equals(reader("all"))){
    				nVUpdate++;
    				//channel = "@MultychatNews";
    				//String mText = Chan.chan()+"\n[" + name + "](" + info+ ")";
    				String mText = Chan.chan()+"\n<a href=\""+info+"\">"+name+"</a>";
    				SendResponse sendResponse = bot.execute(new SendMessage(channel, mText)
    						.parseMode(ParseMode.HTML));
    				//SendResponse sendResponse=bot.sendMessage(channel, Chan.chan()+"\n" + name + "\n\n" + info);
    				Message message = sendResponse.message();
    				App.addWrite("kronos","["+time("yyyy-MM-dd HH:mm:ss.SSS")+"] "+mText+"_"+message.messageId());
    				//mesasge_id = message.messageId();
    				//System.out.print(eLast());	
    				eLast(false);
    				bot.execute(new EditMessageText(channel, lastId, eLast(true))
    						.parseMode(ParseMode.HTML)
    						.disableWebPagePreview(true));
    				//try {
        				writer(mText + "@" + message.messageId(), "Last");
					//} catch(java.lang.NullPointerException e){
						//logger("ERROR WHILE SENDING MESSAGE!");
					//}
    				writer(Convinti, "all");
    				writer(info, "id");
    				//logger("\nNew video: true\n");
    				logger("true\n");
    				//The after-live modifier message is still in alpha.
    				//I'm waiting for the bot 2.0 update of the library.
    				if(inLive==true) {
    					//Clive.t.start();
    					logger("Live founded!");
    					threadst1 = threadst();
    					liveFinish = 2;
    					//try {
    						mesasge_id = message.messageId();
    					logger("New id value: " + mesasge_id);
    					//} catch(java.lang.NullPointerException e){
    						//logger("ERROR WHILE SENDING MESSAGE!");
    					//}
    					nVUpdate++;
    					inLive = false;
    					threadst2 = info;
    					if(Clive.checkUpcoming()){loggerL("Editing programmed live message " + mesasge_id
    							+ " in channel: " + channel + "...");
    						bot.execute(new EditMessageText(channel, mesasge_id,
    							"[Live programmata]\n<a href=\"" + info + "\">" + name + "</a>")
    							.parseMode(ParseMode.HTML)); logger("Done!");}
    					
    				//Clive.checkInLive();
    				}
    			} else {if(comp==true)/*logger("New video: false");*/logger("false");}
    			if(liveFinish==1) {
    				loggerL("Editing ending live message " + mesasge_id + " in channel: " + channel +
    						" with: " + liveEnd + "...");
					BaseResponse bs = bot.execute(new EditMessageText(channel, mesasge_id, "<b>[Live terminata]</b>\n"+liveEnd)
							.parseMode(ParseMode.HTML).disableWebPagePreview(true));
					logger(bs.toString());
    				logger("Done!");
					//Clive.t.stop();
					liveFinish = 0;
					}
    			if(liveFinish>0) liveFinish = 3;
    			if(cristoEVenuto){
    				inLive = true;
    				bot.execute(new EditMessageText(channel, mesasge_id,
							Chan.chan()+"\n<a href=\"" + threadst2 + "\">" + name + "</a>")
							.parseMode(ParseMode.HTML));
    				inLive = false;
    				cristoEVenuto = false;
    			}
    		} else {/*System.out.println("max");*/nLCheck++; Clive.checkInLive();}}
    		inLive = false;
    		} catch(IOException e) {e.printStackTrace();}
    		try{
    		    Thread.sleep(5000);
    		} catch(InterruptedException ex){
    		    Thread.currentThread().interrupt();
    		} tesThread++;	
    	}
    	/*JsonObject jsonObject = new JsonParser().parse(Download.dwn()).getAsJsonObject();
    	System.out.println(jsonObject.get("items").getAsString()); */
    }
    public static Boolean writer(String text, String path){
    	try {
			File file = new File(dir + path);
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
        fileReader = new FileReader(dir + path);
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
    	if(!id.equals(fileCn)) temp = true;// else {logger("\nNop\n");}
    	Convinti = fileCn;
    	if(comp==true)/*logger("Id change: " + temp);*/logger(" " + temp);
    	return temp;
    }
    public static String getInfo(int n) throws JSONException{
    	int i = n + 1;
    	String result = "";
    	try{
    		JSONObject obj = new JSONObject(Download.dwn(api + i));
    		JSONArray arr = obj.getJSONArray("items");
    		obj = arr.getJSONObject(n);
    		result = obj.getJSONObject("id").getString("videoId");
    		name = "";
    		name = toHtml(obj.getJSONObject("snippet").getString("title"));
    		if(
    				obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("live")||
    				obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("upcoming")
    			) {/*logger("TEST");*/ inLive = true;}
    		videoid = "";
    		videoid = result;
    	}catch (ConnectException | JSONException e) {
			e.printStackTrace();
			inLive = false;
			try{
    		    Thread.sleep(5000);
    		} catch(InterruptedException ex){
    		    Thread.currentThread().interrupt();
    		}
			return null;
    	}
        return "https://youtu.be/" + result;
    }
    public static String eLast(Boolean ft) throws IOException{
    	StringTokenizer st = new StringTokenizer(allLine("Last"), "@");
    	String ret = st.nextToken();
    	//System.out.println(ret);
    	if(ft==false){
    		lastId = 0;
    		lastId = Integer.parseInt(st.nextToken());
    	} else {logger("Changing last sended message (" + lastId + ") using: " + allLine("Last"));}
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
    	    	//if(first==true){first=false; ret = line;}
    	    	//System.out.println(line);
    	    	if(first<1){first++;}else{ret = ret + line;}
    	    }
    	}
    	return ret;
    }
    public static void newFile(String path){
    	File file = new File(dir + path);
    	//boolean blnCreated = false;
        try{
          //blnCreated = file.createNewFile();
          file.createNewFile();
        }
        catch(IOException ioe){
        	logger("Error while creating a new empty file :" + ioe);
        }
        //System.out.println(blnCreated);
        //return blnCreated;
    }
    public static boolean exist(String path){
    	File f = new File(dir + path);
    	if(f.exists() && !f.isDirectory()) return true;
    	return false;
    }
	public static void delater(String path){
		try {
		    Files.delete(Paths.get(dir + path));
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    System.err.println(x);
		}
	}
    public static boolean upFile() throws ConnectException{
    	String[] result = Download.dwn("http://stranckutilies.altervista.org/editFile").split(";");
    	boolean b = false;
    	for (int x=0; x<result.length; x++) {
    		String sp[] = result[x].split("\\s+");
    		if(sp[0].equalsIgnoreCase("edit")&&exist(sp[1])) {writer(sp[2], sp[1]);b = true;}
        	if(sp[0].equalsIgnoreCase("new")&&!exist(sp[1])) {newFile(sp[1]);b = true;}
        	if(sp[0].equalsIgnoreCase("delate")&&exist(sp[1])) {delater(sp[1]);b = true;}
        	/*System.out.println(exist(sp[1]));
    		if(sp[0].equalsIgnoreCase("edit")){
    			if(exist(sp[1])) writer(sp[2], sp[1]);
    		}
    		if(sp[0].equalsIgnoreCase("new")){
    			if(!exist(sp[1])) newFile(sp[1]);
    		}
    		if(sp[0].equalsIgnoreCase("delate")){
    			if(exist(sp[1])) delater(sp[1]);
    		}*/
    	}
    	return b;
    }
    public static String aL(String path, boolean slashN) throws FileNotFoundException, IOException{
	    String ret = "";
	    String accapo = "";
	    if(slashN) accapo = "\n";
    	try (BufferedReader br = new BufferedReader(new FileReader(dir + path))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	       ret += line + accapo;
    	    }
    	}
    	return ret;
    }
    public static String toHtml(String s) {
    	String ret = "";
    	for(int n=0; n<s.length(); n++){
    		int ascii = (int) s.charAt(n);
    		ret += "&#" + ascii +";";
    	}
    	return ret;
    }
    public static String fromHtml(String s){
    	String ret = "";
    	String[] sp = s.replaceAll("&#", "").split(";");
    	for(int i=0;i<sp.length;i++){
    		ret += Character.toString((char) Integer.parseInt(sp[i]));
    	}
    	return ret;
    }
    public static int addWrite(String path, String text) throws IOException{
		int i = 0;
		File fout = new File("temp");
		try (BufferedReader br = new BufferedReader(new FileReader(dir + path))) {
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		    String line;
		    //String s = "";
		    //if(linux) s = "\n";
		    while ((line = br.readLine()) != null) {
		    	i++;
		    	bw.write(line);
		    	bw.newLine();
		    }
		    bw.write(text);
		    i++;
			bw.close();
		}
		delater(path);
		fout.renameTo(new File(dir + path));
		return i;
	}
    public static void moveLog(){
    	int i = 0;
    	loggerL("Moving log from CB_old.txt to log/CB");
    	while(true){
    		if(!exist("log/CB" + i + ".txt")){
    			File f = new File("CB_old.txt");
    			f.renameTo(new File("log/CB" + i + ".txt"));
    			logger(i + ".txt");
    			return;
    		} i++;
    	}
    }
    public static boolean checkAdm(Long id) throws IOException{
    	String[] s = aL("admin", false).split(";");
    	for(int i=0;i<s.length;i++){if(s[i].equalsIgnoreCase(id.toString())) return true;}
    	return false;
    }
    public static String time(String format) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    public static void logger(String testo){
    	String s = "";
    	if(tempLine) s = "[" + time("dd-HH:mm:ss") + "] ";
    	if(log==true) System.out.println(s + testo);
    	tempLine = true;
    }
    public static void loggerL(String testo){
    	String s = "";
    	if(tempLine) s = "[" + time("dd-HH:mm:ss") + "] ";
    	if(log==true) System.out.print(s + testo);
    	tempLine = false;
    }
}