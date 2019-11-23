package com.desz.entertainment.rockscissorspaper.functions;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public final class MoveLeger {
	
	private final Move move;
	/*
	 * The Move this beats!
	 */
	public MoveLeger(Move move){
		this.move = move;
	}
	public Move getMove() {
		return move;
	}
	
	
}
