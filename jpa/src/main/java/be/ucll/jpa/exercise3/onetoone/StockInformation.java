package be.ucll.jpa.exercise3.onetoone;

import java.util.Date;

public class StockInformation {

    private Long id;
    private int numberInStock;
    private Date inStockSince;

    private Product product;

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(final int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public Date getInStockSince() {
        return inStockSince;
    }

    public void setInStockSince(final Date inStockSince) {
        this.inStockSince = inStockSince;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }
}
