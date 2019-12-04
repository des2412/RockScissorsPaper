package com.desz.entertainment.rockscissorspaper.builders;

import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.supplyRandomMove;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.Move.PAPER;

public class GameFactory {

	/**
	 * Factory for random players.
	 * 
	 * @author des
	 *
	 */
	private static class RandomPlayerFactory {

		public static List<GamePlayer> getRandomPlayers(int n) {
			return Stream
					.generate(() -> GamePlayer.builder().id("random")
							.moveHandler(MoveHandler.builder().move(supplyRandomMove.get()).build()).build())
					.limit(n).collect(toList());
		}
	}

	public static GameBuilder createGame(GameConfig c) {
		switch (c) {
		case ROCKVSPAPER:
			return GameBuilder.builder()
					.player(GamePlayer.builder().id("rock").moveHandler(MoveHandler.builder().move(ROCK).build())
							.build())

					.opponent(GamePlayer.builder().id("paper").moveHandler(MoveHandler.builder().move(PAPER).build())
							.build())
					.build();
		case ROCKVSRANDOM:
			return GameBuilder.builder()
					.player(GamePlayer.builder().id("rock").moveHandler(MoveHandler.builder().move(ROCK).build())
							.build())
					.randomMoves(1).randomPlayers(RandomPlayerFactory.getRandomPlayers(c.players)).build();
		}
		return null;
	}
}
