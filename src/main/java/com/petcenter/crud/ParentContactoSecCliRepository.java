package com.petcenter.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.petcenter.model.ParentContactoSecCli;

public interface ParentContactoSecCliRepository extends CrudRepository<ParentContactoSecCli, Long> {
	
	List<ParentContactoSecCli> findAll();
	
	ParentContactoSecCli findByIdParentContactoSecCli(long id);

}
