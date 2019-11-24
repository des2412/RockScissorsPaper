package com.desz.entertainment.rockscissorspaper.player;

import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class GamePlayer {
	@NonNull
	private String id;
	@NonNull
	private MoveHandler moveHandler;

}
