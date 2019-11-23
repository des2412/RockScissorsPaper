package com.desz.entertainment.rockscissorspaper.functions;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.PAPER;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.Move.SCISSORS;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.getRandomMove;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestMoveFunction {

	static final MoveHandler rock = MoveHandler.builder().move(ROCK).build();
	static final MoveHandler scissors = MoveHandler.builder().move(SCISSORS).build();
	static final MoveHandler paper = MoveHandler.builder().move(PAPER).build();

	@Test
	public void test_get_random_number_not_null() {
		assertNotNull(getRandomMove.get());
	}

	@Test
	public void test_exp_rock_beats_scissors() {

		assertEquals(ROCK, compareMove.apply(rock, scissors));

	}

	@Test
	public void test_exp_paper_beats_rock() {

		assertEquals(PAPER, compareMove.apply(paper, rock));

	}

	@Test
	public void test_exp_scissors_beats_paper() {

		assertEquals(SCISSORS, compareMove.apply(scissors, paper));

	}

	@Test
	public void test_exp_scissors_and_scissors_equal_draw() {

		assertEquals(DRAW, compareMove.apply(scissors, scissors));

	}

	@Test
	public void test_exp_scissors_and_paper__does_not_equal_draw() {

		assertNotEquals(DRAW, compareMove.apply(scissors, paper));

	}

}
