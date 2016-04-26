package it.TetrisReich.bot.TestBot;

import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import it.TetrisReich.bot.TestBot.App;

public class Chan {
	public static String video;
	public static String live;
	public static String chan() throws IOException{
    	String text = "";
    	if(App.textEsist==true){           
    		Random generator = new Random();
        	toke1();
    		if(App.inLive==true){
    			StringTokenizer st = new StringTokenizer(live, ";");
            	int in = generator.nextInt(3);
    			for(int i=0;i<=in;i++){
    				String str;
    				str = st.nextToken();
    				if(i==in) text = str;
    			}
    		} else {
    			StringTokenizer st = new StringTokenizer(video, ";");
            	int in = generator.nextInt(3);
    			for(int i=0;i<=in;i++){
    				String str;
    				str = st.nextToken();
    				if(i==in) text = str;
    			}
    		}
    	} else if(App.inLive==true)text = "In live ora:"; else text = "Nuovo video:";
    	return text;
    }
	public static String toke1() throws IOException{
		StringTokenizer st = new StringTokenizer(App.reader("text"), "_");
        video = st.nextToken();
        live = st.nextToken();
        return null;
	}
}
