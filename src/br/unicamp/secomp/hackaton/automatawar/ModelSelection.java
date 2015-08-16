package br.unicamp.secomp.hackaton.automatawar;

import java.util.ArrayList;

public class ModelSelection {
	ArrayList<Model> models;
	
	public ModelSelection()
	{
		insertModels();
	}
	
	public Model getModel(int i)
	{
		return models.get(i);
	}
	
	public void addModel(Model m)
	{
		models.add(m);
	}
	
	public void insertModels()
	{
		
		int v[][] =
			{
				{ 0, 0, 0, 0},
				{ 0, 1, 1, 0},
				{ 0, 1, 1, 0},
				{ 0, 0, 0, 0}
			};
		Model m = new Model("Block", v, 4, 4);
		addModel(m);
		
		
		int v2[][] =
			{
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
			};
		m = new Model("Blinker Wall", v2, 5, 5);
		addModel(m);
	}
}
