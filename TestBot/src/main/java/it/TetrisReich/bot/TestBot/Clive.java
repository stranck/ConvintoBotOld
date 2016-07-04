package it.TetrisReich.bot.TestBot;

import it.TetrisReich.bot.TestBot.Download;

import org.json.JSONArray;
import org.json.JSONObject;

import it.TetrisReich.bot.TestBot.App;

public class Clive extends Thread{
	public static boolean incoming = false;
	public static void checkInLive(){
		if(incoming)checkUpcoming();
		App.loggerL("Checking if Live is finished: ");
		JSONObject obj = new JSONObject(Download.dwn(App.threadst1));
		JSONArray arr = obj.getJSONArray("items");
		obj = arr.getJSONObject(0);
		if(obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("none")){
			App.liveEnd = "";
			App.liveEnd = "*[Live terminata]*\n["
					+obj.getJSONObject("snippet").getJSONObject("localized").getString("title")+"]("
					+App.threadst2+")";
			App.liveFinish = 1;
			App.logger("false");
		} else {
			App.logger("true");
			App.liveFinish = 2;
		}
	}
	public static boolean checkUpcoming(){
		JSONObject obj = new JSONObject(Download.dwn(App.threadst1));
		JSONArray arr = obj.getJSONArray("items");
		obj = arr.getJSONObject(0);
		if(incoming&&obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("live")){
			App.cristoEVenuto = true;
			incoming = false;
			return false;
		}
		if(obj.getJSONObject("snippet").getString("liveBroadcastContent").equals("upcoming")){
			incoming = true;
			return true;
		}
		return false;
	}
}
