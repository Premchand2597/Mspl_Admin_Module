package com.example.mspl_connect.Sales_Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Sales_Entity.DropdownValueEntity;
import com.example.mspl_connect.Sales_Repository.DropdownValueRepository;


@Service
public class DropdownValueService {
	    @Autowired
	    private DropdownValueRepository repository;

	    public List<String> getValuesByType(String type) {
	        return repository.findByType(type).stream()
	                .map(DropdownValueEntity::getValue)
	                .collect(Collectors.toList());
	    }

	    public DropdownValueEntity addValue(String type, String value) {
	        DropdownValueEntity entity = new DropdownValueEntity();
	        entity.setType(type);
	        entity.setValue(value);
	        return repository.save(entity);
	    }
}
