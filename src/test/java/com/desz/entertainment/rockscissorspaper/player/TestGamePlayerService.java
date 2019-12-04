package com.desz.entertainment.rockscissorspaper.player;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static org.junit.Assert.*;

import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.supplyRandomMove;

import org.junit.Test;

import com.desz.entertainment.rockscissorspaper.builders.GameBuilder;
import com.desz.entertainment.rockscissorspaper.builders.GameConfig;
import com.desz.entertainment.rockscissorspaper.builders.GameFactory;
import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayService.PlayOutcome;

public class TestGamePlayerService {

	GamePlayService sut;

	/*
	 * @Before public void init() { sut = new
	 * GamePlayService(GameFactory.getConfig(GameConfig.ROCKVSRANDOM)); }
	 */
	// @Test(expected = RuntimeException.class)
	public void test_play_game_expect_runtime_exception() {
		sut = new GamePlayService(GameFactory.createGame(GameConfig.ROCKVSPAPER));
		final GamePlayer rocker = GamePlayer.builder().id("2").moveHandler(MoveHandler.builder().move(ROCK).build())
				.build();
		assertNotNull(sut.playGame(rocker, rocker));
	}

	@Test
	public void test_play_game_expect_winner_or_draw() {

		sut = new GamePlayService(GameFactory.createGame(GameConfig.ROCKVSPAPER));
		final GamePlayer random = GamePlayer.builder().id("1")
				.moveHandler(MoveHandler.builder().move(supplyRandomMove.get()).build()).build();
		final GamePlayer rocker = GamePlayer.builder().id("2").moveHandler(MoveHandler.builder().move(ROCK).build())
				.build();

		final String s = sut.playGame(random, rocker).getResult();

		assertNotNull(s);
		assertTrue(s.contains("Winner") | s.contains(DRAW.name()));

	}

	@Test
	public void test_play_game_expect_paper_beats_rock() {
		
		sut = new GamePlayService(GameFactory.createGame(GameConfig.ROCKVSPAPER));
		final GameBuilder gameBuilder = sut.getGameBuilder();
		final GamePlayer rocker = gameBuilder.getPlayer();
		final GamePlayer paper = gameBuilder.getOpponent();
		PlayOutcome res = sut.playGame(rocker, paper);
		final String s = res.getResult();
		
		assertEquals(0, sut.getGameResult().getScores().get(0).intValue());

		assertNotNull(s);
		assertTrue(res.getResult().contains("PAPER"));

	}

}
