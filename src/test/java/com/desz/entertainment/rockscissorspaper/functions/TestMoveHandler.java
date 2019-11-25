package com.desz.entertainment.rockscissorspaper.functions;

import org.junit.Test;

public class TestMoveHandler {

	@Test(expected = RuntimeException.class)
	public void test_draw_expect_runtime_exception() {
		MoveHandler.builder().move(Move.DRAW).build();
	}

}
