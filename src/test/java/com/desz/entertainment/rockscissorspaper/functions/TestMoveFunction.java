package com.desz.entertainment.rockscissorspaper.functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static com.desz.entertainment.rockscissorspaper.functions.Move.*;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;

import org.junit.Test;

public class TestMoveFunction {

	@Test
	public void test_exp_rock_beats_scissors() {

		assertEquals(ROCK, compareMove.apply(new MoveLeger(ROCK), new MoveLeger(SCISSORS)));

	}

	@Test
	public void test_exp_paper_beats_rock() {

		assertEquals(PAPER, compareMove.apply(new MoveLeger(PAPER), new MoveLeger(ROCK)));

	}

	@Test
	public void test_exp_scissors_beats_paper() {

		assertEquals(SCISSORS, compareMove.apply(new MoveLeger(SCISSORS), new MoveLeger(PAPER)));

	}
	
	@Test
	public void test_exp_scissors_and_scissors_equal_draw() {

		assertEquals(DRAW, compareMove.apply(new MoveLeger(SCISSORS), new MoveLeger(SCISSORS)));

	}
	
	@Test
	public void test_exp_scissors_and_paper__does_not_equal_draw() {

		assertNotEquals(DRAW, compareMove.apply(new MoveLeger(SCISSORS), new MoveLeger(PAPER)));

	}


}
