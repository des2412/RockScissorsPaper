package com.desz.entertainment.rockscissorspaper.functions;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public final class MoveHandler {
	@NonNull
	private final Move move;

	/**
	 * 
	 * @param move The Move this beats.
	 */
	public MoveHandler(Move move) {
		this.move = move;
		if (move.equals(Move.DRAW))
			throw new RuntimeException();
	}

}
