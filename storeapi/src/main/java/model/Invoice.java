package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the invoices database table.
 * 
 */
@Entity
@Table(name="invoices")
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="invoice_id")
	private long invoiceId;

	@Column(name="CIF")
	private String cif;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private byte paid;

	private BigDecimal total;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to ProductInvoice
	@OneToMany(mappedBy="invoice")
	private List<ProductInvoice> productInvoices;

	public Invoice() {
	}

	public long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public byte getPaid() {
		return this.paid;
	}

	public void setPaid(byte paid) {
		this.paid = paid;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ProductInvoice> getProductInvoices() {
		return this.productInvoices;
	}

	public void setProductInvoices(List<ProductInvoice> productInvoices) {
		this.productInvoices = productInvoices;
	}

	public ProductInvoice addProductInvoice(ProductInvoice productInvoice) {
		getProductInvoices().add(productInvoice);
		productInvoice.setInvoice(this);

		return productInvoice;
	}

	public ProductInvoice removeProductInvoice(ProductInvoice productInvoice) {
		getProductInvoices().remove(productInvoice);
		productInvoice.setInvoice(null);

		return productInvoice;
	}

}