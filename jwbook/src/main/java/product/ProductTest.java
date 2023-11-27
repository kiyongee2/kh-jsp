package product;

import java.util.List;

public class ProductTest {

	public static void main(String[] args) {
		ProductService service = new ProductService();
		
		List<Product> productList = service.findAll();
		
		for(Product product : productList) {
			System.out.println(product.getId() + ", " + product.getName());
		}
		
		//상세 정보
		Product prod1 = service.find("101");
		System.out.println(prod1.getName());
	}

}
