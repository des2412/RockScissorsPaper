package com.desz.entertainment.rockscissorspaper.builders;

import java.util.List;

import com.desz.entertainment.rockscissorspaper.player.GamePlayer;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class GameBuilder {

	private int randomMoves;
	private List<GamePlayer> randomPlayers;
	//@NonNull
	private GamePlayer player;
	//@NonNull
	private GamePlayer opponent;
}
