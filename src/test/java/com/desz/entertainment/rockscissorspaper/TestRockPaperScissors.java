package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.RockPaperScissors.DEC_FORMAT;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.IntSummaryStatistics;
import static java.util.Optional.of;
import static java.util.Optional.empty;
import org.junit.Before;
import org.junit.Test;

import com.desz.entertainment.rockscissorspaper.builders.GameConfig;
import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;

public class TestRockPaperScissors {

	RockPaperScissors sut;

	@Before
	public void init() {
		sut = new RockPaperScissors(GameConfig.ROCKVSRANDOM,
				of(GamePlayer.builder().id("rocker").moveHandler(MoveHandler.builder().move(ROCK).build()).build()),
				empty());
	}

	@Test
	public void test_verify_statistics_exp_not_null() {
		final IntSummaryStatistics stats = sut.collectStatistics(asList(1, 0, 1));
		assertNotNull(stats);
		assertEquals(2, stats.getSum());
		assertEquals("0.66", DEC_FORMAT.format(stats.getAverage()));
	}

}
