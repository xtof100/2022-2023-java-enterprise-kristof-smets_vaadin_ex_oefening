package be.ucll.vaadin.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String productId;
	private String productName;
	private String productDescription;
	private BigDecimal productPrice;

	public Product(Long id, String productId, String productName,
			String productDescription, BigDecimal productPrice) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
}