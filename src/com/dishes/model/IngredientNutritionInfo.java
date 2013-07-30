package com.dishes.model;

import org.ksoap2.serialization.SoapObject;

public class IngredientNutritionInfo {

	private String water; // 水分(g)
	private String energy; // 能量(kcal)
	private String protein; // 蛋白质(g)
	private String fat; // 脂肪（g）
	private String cho; // ̼碳水化合物(g)
	private String dietaryFiber; // 膳食纤维(g)
	private String cholesterol; // 胆固醇(mg)
	private String vitaminA; // 维生素A（（µgRE）
	private String vitC; // 维生素C（mg）
	private String vitE; // 维生素E（mg）
	private String eleCa; // 钙（mg）
	private String eleK; // 钾（mg）
	private String inName;
	private String picUrl;


	public IngredientNutritionInfo( SoapObject object ) {

		parse( object );
	}


	private void parse( SoapObject object ) {

		// TODO Auto-generated method stub
		this.inName = object.getPropertySafelyAsString( "inName" );
		this.picUrl = object.getPropertySafelyAsString( "inPic" );
		this.eleK = object.getPropertySafelyAsString( "k" );
		this.water = object.getPropertySafelyAsString( "water" );
		this.energy = object.getPropertySafelyAsString( "energy" );
		this.protein = object.getPropertySafelyAsString( "protein" );
		this.fat = object.getPropertySafelyAsString( "fat" );
		this.cho = object.getPropertySafelyAsString( "cho" );
		this.dietaryFiber = object.getPropertySafelyAsString( "dietaryFiber" );
		this.vitaminA = object.getPropertySafelyAsString( "vitaminA" );
		this.vitC = object.getPropertySafelyAsString( "vitC" );
		this.vitE = object.getPropertySafelyAsString( "vitE" );
		this.eleCa = object.getPropertySafelyAsString( "ca" );
		this.cholesterol = object.getPropertySafelyAsString( "cholesterol" );
	}


	public String getWater() {

		return water;
	}


	public void setWater( String water ) {

		this.water = water;
	}


	public String getEnergy() {

		return energy;
	}


	public void setEnergy( String energy ) {

		this.energy = energy;
	}


	public String getProtein() {

		return protein;
	}


	public void setProtein( String protein ) {

		this.protein = protein;
	}


	public String getFat() {

		return fat;
	}


	public void setFat( String fat ) {

		this.fat = fat;
	}


	public String getCho() {

		return cho;
	}


	public void setCho( String cho ) {

		this.cho = cho;
	}


	public String getDietaryFiber() {

		return dietaryFiber;
	}


	public void setDietaryFiber( String dietaryFiber ) {

		this.dietaryFiber = dietaryFiber;
	}


	public String getCholesterol() {

		return cholesterol;
	}


	public void setCholesterol( String cholesterol ) {

		this.cholesterol = cholesterol;
	}


	public String getVitaminA() {

		return vitaminA;
	}


	public void setVitaminA( String vitaminA ) {

		this.vitaminA = vitaminA;
	}


	public String getVitC() {

		return vitC;
	}


	public void setVitC( String vitC ) {

		this.vitC = vitC;
	}


	public String getVitE() {

		return vitE;
	}


	public void setVitE( String vitE ) {

		this.vitE = vitE;
	}


	public String getEleCa() {

		return eleCa;
	}


	public void setEleCa( String eleCa ) {

		this.eleCa = eleCa;
	}


	public String getInName() {

		return inName;
	}


	public void setInName( String inName ) {

		this.inName = inName;
	}


	public String getPicUrl() {

		return picUrl;
	}


	public void setPicUrl( String picUrl ) {

		this.picUrl = picUrl;
	}


	public String getEleK() {

		return eleK;
	}


	public void setEleK( String eleK ) {

		this.eleK = eleK;
	}
}
