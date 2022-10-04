package com.test.customer.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {

	MALE(0), FEMALE(1), OTHER(2);

	public int value;
	
	public static Gender valueOf(int value) {
		// Find value.
		for (Gender val : Gender.values()) {
			if (val.value == value) {
				return val;
			}
		}
		// Not found.
		return null;
	}

}
