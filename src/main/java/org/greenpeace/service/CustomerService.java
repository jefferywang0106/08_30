package org.greenpeace.service;

import java.util.List;

import org.greenpeace.model.CustomerDto;
import org.greenpeace.model.ProductDto;

public interface CustomerService {

	CustomerDto logic(List<ProductDto> pro);

	boolean judgeExist(CustomerDto data);

}