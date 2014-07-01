package com.homenet.refactor;

public class Person {
	private String name;
	private String phoneNumber;
	

    public Person(String name, String phoneNumder) {
        this.name = name;
        this.phoneNumber = phoneNumder;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
