package com.desz.entertainment.rockscissorspaper.builders;


public enum GameConfig {

	ROCKVSRANDOM(100, false), ROCKVSPAPER(0, true);

	final int players;
	final boolean singleOpponent;

	GameConfig(int players, boolean isSingleOpponent) {
		this.players = players;
		this.singleOpponent = isSingleOpponent;
	}

	public int getPlayers() {
		return players;
	}

	public boolean isSingleOpponent() {
		return singleOpponent;
	}

	
	
	

}
