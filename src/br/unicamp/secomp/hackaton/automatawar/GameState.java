package br.unicamp.secomp.hackaton.automatawar;

public class GameState {
	private char[][] states;
	private int y, x;
	
	GameState(int y, int x)
	{
		states = new char[y][x];
	}
	
	public char[][] getStates()
	{
		return states;
	}
	
	public int getHeight()
	{
		return y;
	}
	
	public int getWidth()
	{
		return x;
	}
	
	public void compute()
	{
		
	}
}
