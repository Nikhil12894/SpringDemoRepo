package com.howtodoinjava.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_EMPLOYEES_ADDRESS")
public class EmployeeAddress {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="city_name")
    private String cityName;
    
    @Column(name="address_line_1")
    private String addressLine1;
    
    @Column(name="address_line_2")
    private String addressLine2;
    
    @Column(name="state_name")
    private String stateName;

    @ManyToOne()
    @JoinColumn(name="employee_id")    
    private EmployeeEntity employeeEntity;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "EmployeeAddress [id=" + id + ", cityName=" + cityName + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", stateName=" + stateName + "]";
	}
    
}
