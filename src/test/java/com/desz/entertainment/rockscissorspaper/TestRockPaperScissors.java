package com.desz.entertainment.rockscissorspaper;

import static org.junit.Assert.*;

import static java.util.Arrays.asList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static com.desz.entertainment.rockscissorspaper.functions.Move.*;
import com.desz.entertainment.rockscissorspaper.functions.Move;
import com.desz.entertainment.rockscissorspaper.functions.MoveLeger;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;

public class TestRockPaperScissors {

	RockPaperScissors sut;

	@Before
	public void init() {
		sut = new RockPaperScissors();
	}

	@Test
	public void testGetRandomMove() {

		assertNotNull(sut.getRandomMove());

	}

	@Test
	public void test_play_game_expect_winner_or_draw() {
		RockPaperScissors sut = new RockPaperScissors();

		MoveLeger rnd = MoveLeger.builder().move(sut.getRandomMove()).build();

		MoveLeger roc = MoveLeger.builder().move(ROCK).build();

		GamePlayer random = GamePlayer.builder().id("1").isRandom(true).move(rnd).build();
		GamePlayer rocker = GamePlayer.builder().id("2").isRandom(false).move(roc).build();

		List<GamePlayer> players = asList(new GamePlayer[] { random, rocker });

		String s = sut.playGame(players);

		assertNotNull(s);
		assertTrue(s.contains("winner") | s.contains("draw"));

	}

	@Test
	public void test_play_game_expect_winner() {
		RockPaperScissors sut = new RockPaperScissors();

		MoveLeger pap = MoveLeger.builder().move(PAPER).build();

		MoveLeger roc = MoveLeger.builder().move(ROCK).build();

		GamePlayer paper = GamePlayer.builder().id("1").isRandom(false).move(pap).build();
		GamePlayer rocker = GamePlayer.builder().id("2").isRandom(false).move(roc).build();

		List<GamePlayer> players = asList(new GamePlayer[] { paper, rocker });

		String s = sut.playGame(players);

		assertNotNull(s);
		assertTrue(s.contains("winner"));

	}

	@Test
	public void test_play_game_expect_draw() {
		RockPaperScissors sut = new RockPaperScissors();

		// MoveLeger pap = MoveLeger.builder().move(Move.PAPER).build();

		MoveLeger roc = MoveLeger.builder().move(ROCK).build();

		GamePlayer rocker = GamePlayer.builder().id("2").isRandom(false).move(roc).build();

		List<GamePlayer> players = asList(new GamePlayer[] { rocker, rocker });

		String s = sut.playGame(players);

		assertNotNull(s);
		assertTrue(s.contains("draw"));

	}

}
