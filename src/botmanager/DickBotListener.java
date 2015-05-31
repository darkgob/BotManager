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
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author DarkGob
 */
public class DickBotListener extends ListenerAdapter<PircBotX> {

	public static Logger log = LoggerFactory.getLogger(DickBotListener.class);

	@Override
	public void onMessage(MessageEvent event) throws Exception {
		event.respond("GAY FARTS");
	}

	@Override
	public void onConnect(ConnectEvent<PircBotX> event) throws Exception {
		event.getBot().getUserBot().send().mode("+B");
	}
	
	public DickBotListener() {
	}
}