package com.talentica.talentpool.models;

public class PositionErrorDTO {
	private String positionIRCCode;
	private String errorMsg;

	public String getPositionIRCCode() {
		return positionIRCCode;
	}

	public void setPositionIRCCode(String positionIRCCode) {
		this.positionIRCCode = positionIRCCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
