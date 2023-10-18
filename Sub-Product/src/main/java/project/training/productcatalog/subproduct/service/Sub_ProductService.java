package project.training.productcatalog.subproduct.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.training.productcatalog.subproduct.dto.Sub_ProductRequest;
import project.training.productcatalog.subproduct.dto.Sub_ProductResponse;
import project.training.productcatalog.subproduct.entity.Sub_Product;
import project.training.productcatalog.subproduct.repo.Sub_ProductRepository;

@Service
@RequiredArgsConstructor
public class Sub_ProductService {
	
	//instance of sub-product repository 
	private final Sub_ProductRepository repo;
	
	
	//adding sub-product to database 
	public Sub_Product addSubproduct(Sub_ProductRequest productRequest)
	{
		Sub_Product product = mapToRequest(productRequest);
		repo.save(product);
		
		return product;
	}
	
	//getting all data from the sub-product table
	public List<Sub_ProductResponse> getAll()
	{
		List<Sub_Product> list = repo.findAll();
		
		List<Sub_ProductResponse> rlist = list.stream().map(prod -> mapToResponse(prod)).toList();
		
		return rlist;
		
	}
	
	//converting sub-product to sub-product response object to send the data to angular
	private Sub_ProductResponse mapToResponse(Sub_Product product)
	{
		Sub_ProductResponse productResponse = Sub_ProductResponse.builder()
												.sname(product.getSname())
												.desp(product.getDesp())
												.validity(product.getValidity())
												.build();
		return productResponse;
		
	}
	
	//converting sub-product request object into sub-product object for inserting into database
	private Sub_Product mapToRequest(Sub_ProductRequest productRequest)
	{
		Sub_Product product = Sub_Product.builder()
											.sname(productRequest.getSname())
											.desp(productRequest.getDesp())
											.validity(productRequest.getValidity())
											.build();
		return product;
		
	}
	
	//checking whether sub-product name is present or not..
	public List<Sub_ProductResponse> checking(String sname)
	{
		List<Sub_Product> list =  repo.findBySname(sname);
		
		List<Sub_ProductResponse> rlist = list.stream().map(prod -> mapToResponse(prod)).toList();
		
		return rlist;

	}

}
