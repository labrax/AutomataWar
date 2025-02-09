package br.unicamp.secomp.hackaton.automatawar;

import java.util.ArrayList;

public class Numbers {
	ArrayList<Model> models;
	
	public Numbers()
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
		int v0[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 1, 1, 0}
			};
		Model m = new Model("0", v0, 5, 5);
		addModel(m);
		
		int v[][] =
			{
				{ 0, 0, 1, 0, 0},
				{ 0, 1, 1, 0, 0},
				{ 0, 0, 1, 0, 0},
				{ 0, 0, 1, 0, 0},
				{ 0, 0, 1, 0, 0},
			};
		m = new Model("1", v, 5, 5);
		addModel(m);
		
		int v2[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 0, 0},
				{ 0, 1, 1, 1, 0}
			};
		m = new Model("2", v2, 5, 5);
		addModel(m);
		
		int v3[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 1, 1, 1, 0}
			};
		m = new Model("3", v3, 5, 5);
		addModel(m);
		
		int v4[][] =
			{
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 0, 0, 1, 0}
			};
		m = new Model("4", v4, 5, 5);
		addModel(m);
		
		int v5[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 0, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 1, 1, 1, 0}
			};
		m = new Model("5", v5, 5, 5);
		addModel(m);
		
		int v6[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 0, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 1, 1, 0}
			};
		m = new Model("6", v6, 5, 5);
		addModel(m);
		
		int v7[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 0, 0, 1, 0}
			};
		m = new Model("7", v7, 5, 5);
		addModel(m);
		
		int v8[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 1, 1, 0}
			};
		m = new Model("8", v8, 5, 5);
		addModel(m);
		
		int v9[][] =
			{
				{ 0, 1, 1, 1, 0},
				{ 0, 1, 0, 1, 0},
				{ 0, 1, 1, 1, 0},
				{ 0, 0, 0, 1, 0},
				{ 0, 1, 1, 1, 0}
			};
		m = new Model("9", v9, 5, 5);
		addModel(m);
	}
}
