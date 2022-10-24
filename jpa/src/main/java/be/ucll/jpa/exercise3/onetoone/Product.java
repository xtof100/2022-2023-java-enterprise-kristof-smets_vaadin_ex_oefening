package be.ucll.jpa.exercise3.onetoone;

import java.io.Serializable;

public class Product implements Serializable {

	private Long id;

	private String productName;
	private String productId;

	private StockInformation stockInformation;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	public StockInformation getStockInformation() {
		return stockInformation;
	}

	public void setStockInformation(final StockInformation stockInformation) {
		this.stockInformation = stockInformation;
		//stockInformation.setProduct(this);
	}
}
