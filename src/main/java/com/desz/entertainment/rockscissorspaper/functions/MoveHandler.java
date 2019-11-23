package com.desz.entertainment.rockscissorspaper.functions;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public final class MoveHandler {

	private final Move move;

	/**
	 * 
	 * @param move The Move this beats.
	 */
	public MoveHandler(Move move) {
		this.move = move;
	}

	/**
	 * 
	 * @return the Move.
	 */
	public Move getMove() {
		return move;
	}

}
