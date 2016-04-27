package it.TetrisReich.bot.TestBot;

import it.TetrisReich.bot.TestBot.Download;

import org.json.JSONArray;
import org.json.JSONObject;

import it.TetrisReich.bot.TestBot.App;

public class Clive extends Thread{
	static Thread t=new Thread(){
		public void run(){
			App.logger(App.threadst);
			Boolean live = true;
			while(live){
				JSONObject obj = new JSONObject(Download.dwn(App.threadst));
		    	JSONArray arr = obj.getJSONArray("items");
		    	obj = arr.getJSONObject(0);
		    	if(obj.getString("liveBroadcastContent").equals("none")){
		    		//Finché non aggiornano la libreria del bot di telegram mi devo tenere questo obbrobbio in mezzo al codice
Download.dwn("https://api.telegram.com/bot197939074:AAG8AeKyywRv-Z0H5TP4kJgat16DSrGtMIQ/editMessageText?chat_id=@Multychatbot&message_id="+App.mesasge_id+"&parse_mode=Markdown&disable_web_page_preview=true&text=*[Live terminata]*\n_"+obj.getJSONObject("localized").getString("title")+"_\n"+App.threadst1);
		    		App.liveFinish = 1;
					//change message text in
		    		/* [LIVE TERMINATA]
		    		 * <link with no anteprima>
		    		 */
		    	}

			}
			return;
		}
	};
}
