package com.vtw.pleiades.center.common.web.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ValidationResult {

	private boolean valid;
	private String invalidMessage;

	public ValidationResult() {
	}

	public ValidationResult(boolean valid) {
		this.valid = valid;
	}

	public ValidationResult(String invalidMessage) {
		this.valid = false;
		this.invalidMessage = invalidMessage;
	}

	public static ValidationResult valid() {
		return new ValidationResult(true);
	}

	public static ValidationResult invalid(String invalidMessage) {
		return new ValidationResult(invalidMessage);
	}

	public boolean isValid() {
		return valid;
	}
	
	@JsonIgnore
	public boolean isInvalid() {
		return !valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}
}
