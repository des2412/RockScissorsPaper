package com.desz.entertainment.rockscissorspaper.player;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlayer {
	
	GamePlayer player;

	@Test(expected=NullPointerException.class)
	public void test() {
		GamePlayer.builder().id("").build();
	}
	
	@Test(expected=NullPointerException.class)
	public void test2() {
		GamePlayer.builder().id("").isRandom(true).build();
	}
}
