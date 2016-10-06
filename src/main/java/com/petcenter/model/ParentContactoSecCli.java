package com.petcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_parentContactoSecCli")
@Data
public class ParentContactoSecCli {
	
	@Id
	@GeneratedValue
	@Column(name="idparentcontactoseccli")
	private long idParentContactoSecCli;
	
	@Column(name="descparentcontactosec")
	private String descParentContactoSec;
	
}
