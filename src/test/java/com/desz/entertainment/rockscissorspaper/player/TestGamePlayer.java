package com.desz.entertainment.rockscissorspaper.player;

import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static org.junit.Assert.*;

import org.junit.Test;

import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;

public class TestGamePlayer {

	GamePlayer player;

	@Test
	public void test_expect_ok() {
		assertNotNull(GamePlayer.builder().id("").moveHandler(MoveHandler.builder().move(ROCK).build()).build());
	}

	@Test(expected = NullPointerException.class)
	public void test_when_null_id_exp_null() {
		GamePlayer.builder().moveHandler(MoveHandler.builder().move(ROCK).build()).build();
	}

	@Test(expected = NullPointerException.class)
	public void test_when_null_movehandler_exp_null() {
		GamePlayer.builder().id("").build();
	}
}
