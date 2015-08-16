package br.unicamp.secomp.hackaton.automatawar;

public class Model {
	private int[][] map;
	private int width, height;
	private String name;
	
	public Model(String name, int[][] map, int height, int width)
	{
		this.map = map;
		this.height = height;
		this.width = width;
		this.name = name;
	}
	
	public int[][] getMap()
	{
		return map;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public String getName()
	{
		return name;
	}
}
