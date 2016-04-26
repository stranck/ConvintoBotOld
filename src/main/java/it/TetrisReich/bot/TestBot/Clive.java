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
