package project.training.productcatalog.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.training.productcatalog.product.dto.ProductResponse;
import project.training.productcatalog.product.model.Product;
import project.training.productcatalog.product.model.Sub_Product;
import project.training.productcatalog.product.rep.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
		
	private final ProductRepository repoP;
	
	
	public Product insertProduct(Product product)
	{
		System.out.println("Insert works");
		repoP.save(product);
		return product;
	}
	
	public List<ProductResponse> display()
	{
		List<Product> list =  repoP.findProduct("approve");
		
		List<ProductResponse> rlist = list.stream().map(prod->mapToProductResponse(prod)).toList();
		
		return rlist;
	}

	private ProductResponse mapToProductResponse(Product prod)
	{
		System.out.println("Mapping to response");
		ProductResponse pr = ProductResponse.builder()
								.pname(prod.getPname())
								.features(prod.getFeatures())
								.category(prod.getCategory())
								.recommended(prod.getRecommended())
								.subproducts(prod.getSubproducts())
								.validity(prod.getValidity())
								.build();
		return pr;
	}
	
	public List<Product> checkProductNameExisting(String pname, String category)
	{
		System.out.println("Checking product name and its category");
		return repoP.findByPnameCategory(pname, category);
	}
	
	public void deleteProduct(String pname, String category) throws InterruptedException
	{	
		System.out.println("Remove works");
		repoP.removeProductItem(pname, category);

	}
	
	public ProductResponse getByCategoryAndName(String pname, String category)
	{
		System.out.println(pname +" "+category);
		System.out.println("Getting single data works");
		Product prod =  repoP.findByCategoryPname(pname, category);
		
		System.out.println(prod);
		
		ProductResponse rlist = mapToProductResponse(prod);
		
		return rlist;
	}
	
	public ProductResponse updatingProduct(ProductResponse productResponse)
	{
		String pname = productResponse.getPname();
		String category = productResponse.getCategory();
		
		Product product = Product.builder()
							.pname(productResponse.getPname())
							.category(productResponse.getCategory())
							.recommended(productResponse.getRecommended())
							.validity(productResponse.getValidity())
							.features(productResponse.getFeatures())
							.subproducts(productResponse.getSubproducts())
							.build();
		
		repoP.removeProductItem(pname, category);
		
		repoP.save(product);
		
		
		
		return productResponse;
	}
	
	public List<ProductResponse> searchByCategory(String pname)
	{
		List<Product> list =  repoP.findByPname(pname);
		
		List<ProductResponse> rlist = list.stream().map(prod -> mapToProductResponse(prod)).toList();
		
		return rlist;
	}
	
	public void statusUpdating(String status, String pname, String category)
	{
		repoP.updateProduct(status, pname, category);
	}
}
