package com.ogg.erp.model;

public class Product
{
	private int productId;
	private String model;
	private String name;
	private String color;
	private int count;
	private float price;
	private int carton;
	private String commonts;
	private float cbm;
	private float weight;
	private String picture;

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public int getCarton()
	{
		return carton;
	}

	public void setCarton(int carton)
	{
		this.carton = carton;
	}

	public String getCommonts()
	{
		return commonts;
	}

	public void setCommonts(String commonts)
	{
		this.commonts = commonts;
	}

	public float getCbm()
	{
		return cbm;
	}

	public void setCbm(float cbm)
	{
		this.cbm = cbm;
	}

	public float getWeight()
	{
		return weight;
	}

	public void setWeight(float weight)
	{
		this.weight = weight;
	}

	public String getPicture()
	{
		return picture;
	}

	public void setPicture(String picture)
	{
		this.picture = picture;
	}

	@Override
	public String toString()
	{
		return "Product [productId=" + productId + ", model=" + model + ", name=" + name + ", color=" + color
		        + ", count=" + count + ", price=" + price + ", carton=" + carton + ", commonts=" + commonts + ", cbm="
		        + cbm + ", weight=" + weight + ", picture=" + picture + "]";
	}

}