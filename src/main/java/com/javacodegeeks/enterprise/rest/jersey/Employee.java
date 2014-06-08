package com.javacodegeeks.enterprise.rest.jersey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
 
@NamedQueries({
	@NamedQuery(
	name = "findEmployeeById",
	query = "from Employee em where em.id = :id"
	)
}) 

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	public enum EmployeeType {Waiter(1), Barman(2), Manager(3);
		private int type;
		
		private EmployeeType(int type) {
			this.type = type;
		}
	};
 
    @Id
    @GeneratedValue
    private Long id;
 
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private EmployeeType type; 
    
    @Column(name="firstname")
    private String firstname;
     
    @Column(name="lastname")
    private String lastname;
     
    public Employee() {
         
    }
     
    public Employee(String firstname, String lastname, EmployeeType type) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setType(type);
         
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

}