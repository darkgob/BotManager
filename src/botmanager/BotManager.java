/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botmanager;

import java.io.IOException;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author DarkGob
 */
public class BotManager extends ListenerAdapter<PircBotX> {

	public static Logger log = LoggerFactory.getLogger(BotManager.class);

	@Override
	public void onMessage(MessageEvent event) throws Exception {
		event.respond("GAY FARTS");
	}

	public static void main(String[] args) throws Exception {
		Configuration<PircBotX> dickConfig = new Configuration.Builder<>()
				.setName("DickBot") //Set the nick of the bot. CHANGE IN YOUR CODE
				.setRealName("DickBot")
				.setLogin("dickBot") //login part of hostmask, eg name:login@host
				.setAutoNickChange(true) //Automatically change nick when the current one is in use
				.addListener(new BotManager()) //This class is a listener, so add it to the bots known listeners
				.setServerHostname("irc.foonetic.net")
				.addAutoJoinChannel("#megadick")
				.setAutoReconnect(true)
				.buildConfiguration();
		//bot.connect throws various exceptions for failures
		try {
			PircBotX dickbot = new PircBotX(dickConfig);
			dickbot.startBot();
			dickbot.getUserBot().send().mode("+B");
		} //In your code you should catch and handle each exception seperately,
		//but here we just lump them all together for simpliciy
		catch (IOException | IrcException ex) {
			log.error("Failed to start bot", ex);
		}
	}
}