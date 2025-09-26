package com.example.mspl_connect.Sales_Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dropdown_values")
public class DropdownValueEntity {
	 public DropdownValueEntity() {
		super();
	}

	public DropdownValueEntity(Long id, String type, String value) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String type; // e.g., "technology", "segment", "looking_for"

	    private String value;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
}
