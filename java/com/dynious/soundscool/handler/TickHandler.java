package com.dynious.soundscool.handler;

import com.dynious.soundscool.client.audio.SoundPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class TickHandler
{
	boolean paused = false;

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		if(paused == false && Minecraft.getMinecraft().isGamePaused())
		{
			SoundPlayer.pauseSounds();
			paused = true;
		}
		if(paused == true && !Minecraft.getMinecraft().isGamePaused())
		{
			SoundPlayer.resumeSounds();
			paused = false;
		}
	}
}