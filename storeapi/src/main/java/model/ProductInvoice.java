package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the product_invoice database table.
 * 
 */
@Entity
@Table(name="product_invoice")
@NamedQuery(name="ProductInvoice.findAll", query="SELECT p FROM ProductInvoice p")
public class ProductInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="detail_id")
	private long detailId;

	private int quantity;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="invoice_id")
	private Invoice invoice;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public ProductInvoice() {
	}

	public long getDetailId() {
		return this.detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}