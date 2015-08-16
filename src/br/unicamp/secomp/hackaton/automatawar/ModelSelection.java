package br.unicamp.secomp.hackaton.automatawar;

import java.util.ArrayList;

public class ModelSelection {
	ArrayList<Model> models;
	
	public ModelSelection()
	{
		models = new ArrayList<Model>();
		insertModels();
	}
	
	public int getAmount()
	{
		return models.size();
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
		
		int v3[][] =
			{
				{0, 0, 0, 0, 0, 0},
				{0, 0, 1, 1, 0, 0},
				{0, 1, 0, 0, 1, 0},
				{0, 0, 1, 1, 0, 0},
				{0, 0, 0, 0, 0, 0}
			};
		m = new Model("Beehive", v3, 5, 6);
		addModel(m);
		
		int v4[][] =
			{
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 0},
				{0, 0, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0}
			};
		m = new Model("Glider Nordeste", v4, 5, 5);
		addModel(m);
		
		int v5[][] =
			{
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 0},
				{0, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0}
			};
		m = new Model("Glider Noroeste", v5, 5, 5);
		addModel(m);
		
		int v6[][] =
			{
				{0 ,0 ,0 ,0 ,0},
				{0 ,0 ,1 ,0 ,0},
				{0 ,0 ,0 ,1 ,0},
				{0 ,1 ,1 ,1 ,0},
				{0 ,0 ,0 ,0 ,0}
			};
		m = new Model("Glider Sudeste", v6, 5, 5);
		addModel(m);
		
		int v7[][] =
			{
				{0 ,0 ,0 ,0 ,0},
				{0 ,0 ,1 ,0 ,0},
				{0 ,1 ,0 ,0 ,0},
				{0 ,1 ,1 ,1 ,0},
				{0 ,0 ,0 ,0 ,0}
			};
		m = new Model("Glider Sudoeste", v7, 5, 5);
		addModel(m);
	}
}
