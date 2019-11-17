package es.lm.enums;

public enum KindEnum {
	BOOK(0), 
	FOOD(1), // No se usa
	MEDICAL_PRODUCT(2), 
	OTHER(3);

	private final Integer code;

	KindEnum(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public String getValueName() {
		return this.name();
	}

}
