/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botmanager;

import static botmanager.DickBotListener.log;
import com.google.common.collect.ImmutableSortedSet;
import java.io.IOException;
import org.pircbotx.Configuration;
import org.pircbotx.MultiBotManager;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.managers.ThreadedListenerManager;

/**
 *
 * @author Chris
 */
public class BotManager {
	
	private static String serverName = "irc.foonetic.net";
	private static String channelName = "#megadick";

	public static void main(String[] args) throws Exception {
		MultiBotManager mgr = new MultiBotManager();
		ThreadedListenerManager listenerMgr = new ThreadedListenerManager();

		Configuration<PircBotX> dickConfig = config("DickBot","DickBot","DickBot",new DickBotListener());
		PircBotX dickBot = new PircBotX(dickConfig);
		DickBotListener dbl = new DickBotListener();
		listenerMgr.addListener(dbl);
		mgr.addBot(dickBot);
		
		Configuration<PircBotX> megadickConfig = config("MegaDICK","MegaDICK","MegaDICK",new MegaDICKListener());
		PircBotX megaDICK = new PircBotX(megadickConfig);
		MegaDICKListener mdBot = new MegaDICKListener();
		listenerMgr.addListener(mdBot);
		mgr.addBot(megaDICK);
		
		mgr.start();
	}
	
	private static Configuration<PircBotX> config (String nick, String realName, String login, ListenerAdapter<PircBotX> listener) {
		Configuration<PircBotX> botConfig = new Configuration.Builder<>()
				.setName(nick) 
				.setRealName(realName)
				.setLogin(login) //login part of hostmask, eg name:login@host
				.setAutoNickChange(true) //Automatically change nick when the current one is in use
				.addListener(listener) //This class is a listener, so add it to the bots known listeners
				.setServerHostname(serverName)
				.addAutoJoinChannel(channelName)
				.setAutoReconnect(true)
				.buildConfiguration();
		return botConfig;
	}
}