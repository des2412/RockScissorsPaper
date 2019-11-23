package com.desz.entertainment.rockscissorspaper.functions;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.desz.entertainment.rockscissorspaper.functions.Move.*;

public interface MoveFunction {

	static Move meth(MoveLeger m1, MoveLeger m2) {
		switch (m1.getMove()) {
		case ROCK:
			return m2.getMove().equals(SCISSORS) ? ROCK : m2.getMove();
		case SCISSORS:
			return m2.getMove().equals(PAPER) ? SCISSORS : m2.getMove();
		case PAPER:
			return m2.getMove().equals(ROCK) ? PAPER : m2.getMove();
		default:
			break;

		}
		return null;
	}
	
	BiFunction<MoveLeger, MoveLeger, Move> compareMove = (m1, m2) -> {
		return m2.getMove().equals(m1.getMove()) ? DRAW : meth(m1, m2);
	
	};
	
	Supplier<Move> getRandomMove = () -> {
		Move[] moves = Move.values();
		Random random = new Random();
		int index = random.nextInt(moves.length);
		Move move = moves[index];
		return move;//.equals(DRAW) ? getRandomMove.get() : move;
	};
	
	

}
