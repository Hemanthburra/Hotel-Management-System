package com.HotelManagementSystem.models;

public class User {
	  private String userName;
	  private String password;
	  private String name;
	  private int age;

	  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// Getters and Setters
	  public String getUserName() {
	    return userName;
	  }

	  public void setUserName(String userName) {
	    this.userName = userName;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }
	}