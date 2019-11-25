package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.PAPER;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;
import static com.desz.entertainment.rockscissorspaper.RockPaperScissors.DEC_FORMAT;

public class TestRockPaperScissors {

	RockPaperScissors sut;

	@Before
	public void init() {
		sut = new RockPaperScissors(
				GamePlayer.builder().id("rocker").moveHandler(MoveHandler.builder().move(ROCK).build()).build());
	}

	@Test
	public void test_verify_statistics_exp_not_null() {
		final IntSummaryStatistics stats = sut.verifyStats(asList(1, 0, 1));
		assertNotNull(stats);
		assertEquals(2, stats.getSum());
		assertEquals("0.66", DEC_FORMAT.format(stats.getAverage()));
	}

	@Test
	public void test_exp_not_null() {

		assertNotNull(sut.getRandomMove());

	}

	@Test
	public void test_never_draw() {
		IntStream.rangeClosed(0, 50).forEach(new IntConsumer() {

			@Override
			public void accept(int value) {

				assertFalse(sut.getRandomMove().equals(DRAW));

			}

		});

	}

	@Test(expected = RuntimeException.class)
	public void test_play_game_expect_runtime_exception() {
		final GamePlayer rocker = GamePlayer.builder().id("2").moveHandler(MoveHandler.builder().move(ROCK).build())
				.build();
		sut.playGame(asList(new GamePlayer[] { rocker, rocker, rocker }));
	}

	@Test
	public void test_play_game_expect_winner_or_draw() {

		final GamePlayer random = GamePlayer.builder().id("1")
				.moveHandler(MoveHandler.builder().move(sut.getRandomMove()).build()).build();
		final GamePlayer rocker = GamePlayer.builder().id("2").moveHandler(MoveHandler.builder().move(ROCK).build())
				.build();

		final List<GamePlayer> players = asList(new GamePlayer[] { random, rocker });

		final String s = sut.playGame(players);

		assertNotNull(s);
		assertTrue(s.contains("Winner") | s.contains("Draw"));

	}

	@Test
	public void test_play_game_expect_winner() {

		GamePlayer paper = GamePlayer.builder().id("1").moveHandler(MoveHandler.builder().move(PAPER).build()).build();
		GamePlayer rocker = GamePlayer.builder().id("2").moveHandler(MoveHandler.builder().move(ROCK).build()).build();

		List<GamePlayer> players = asList(new GamePlayer[] { paper, rocker });

		String s = sut.playGame(players);

		assertNotNull(s);
		assertTrue(s.contains("Winner"));

	}

	@Test
	public void test_play_game_expect_draw() {

		GamePlayer rocker = GamePlayer.builder().id("2").moveHandler(MoveHandler.builder().move(ROCK).build()).build();

		final List<GamePlayer> players = asList(new GamePlayer[] { rocker, rocker });

		final String s = sut.playGame(players);

		assertNotNull(s);
		assertTrue(s.contains("Draw"));

	}

}
