package com.shopme.common.entity.order;

import java.util.*;

import com.shopme.common.entity.AbstractAddress;
import com.shopme.common.entity.Address;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.OrderTrack;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order extends AbstractAddress {
	
	
	@Column(nullable = false, length = 45)
	private String country;

	private Date orderTime;
	
	private float shippingCost;
	private float productCost;
	private float subtotal;
	private float tax;
	private float total;
	
	private int deliverDays;
	private Date deliverDate;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	//One To many bc order can have many details
	//Cascade type so it will also save/update the children which is the order detail
	//orphan reooval deletes orphans from collection
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OrderDetail> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("updatedTime ASC")
    private List <OrderTrack> orderTracks = new ArrayList<>();

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}

	public float getProductCost() {
		return productCost;
	}

	public void setProductCost(float productCost) {
		this.productCost = productCost;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getDeliverDays() {
		return deliverDays;
	}

	public void setDeliverDays(int deliverDays) {
		this.deliverDays = deliverDays;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public void copyAddressFromCustomer() {
		setFirstName(customer.getFirstName());
		setLastName(customer.getLastName());
		setPhoneNumber(customer.getPhoneNumber());
		setAddressLine1(customer.getAddressLine1());
		setAddressLine2(customer.getAddressLine2());
		setCity(customer.getCity());
		setCountry(customer.getCountry().getName());
		setPostalCode(customer.getPostalCode());
		setState(customer.getState());
	}

	@Override
	public String toString() {
		return "Order [Id=" + id + ", subtotal=" + subtotal + ", paymentMethod=" + paymentMethod + ", status=" + status
				+ ", customer=" + customer.getFullName() + "]";
	}
	
	@Transient
	public String getDestination() {
		String destination =  city + ", ";
		if (state != null && !state.isEmpty()) destination += state + ", ";
		destination += country;
		
		return destination;
	}

	public void copyShippingAddress(Address address) {
		setFirstName(address.getFirstName());
		setLastName(address.getLastName());
		setPhoneNumber(address.getPhoneNumber());
		setAddressLine1(address.getAddressLine1());
		setAddressLine2(address.getAddressLine2());
		setCity(address.getCity());
		setCountry(address.getCountry().getName());
		setPostalCode(address.getPostalCode());
		setState(address.getState());
		
	}
	
	@Transient
	public String getShippingAddress() {
		String address = firstName;
		if (lastName != null && !lastName.isEmpty()) address += " " + lastName;
		
		if(!addressLine1.isEmpty()) address += ", " + addressLine1;
		
		if (addressLine2 != null && !addressLine2.isEmpty()) address += ", " + addressLine2;
		
		if(!city.isEmpty()) address += ", " + city;
		
		if (state != null && !state.isEmpty()) address += ", " + state;
		
		address += ", " + country;
		
		if (postalCode.isEmpty()) address += ". Postal Code: " + postalCode;
		if (phoneNumber.isEmpty()) address += ". Phone Number: " + phoneNumber;
		
		return address;
	}

	public List<OrderTrack> getOrderTracks() {
		return orderTracks;
	}

	public void setOrderTracks(List<OrderTrack> orderTracks) {
		this.orderTracks = orderTracks;
	}
	
	//Formatting date object based on pattern
	@Transient
	public String getDeliverDateOnForm() {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormatter.format(this.deliverDate);
	}
	
	//Passing datestring to dateformatter, passing datestring to dateobject 
	//and saving it to deliver date
	public void setDeliverDateOnForm(String dateString) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			this.deliverDate = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//For adding recipients name for each order 
	@Transient
	public String getRecipientName() {
		String name = firstName;
		if (lastName != null && !lastName.isEmpty()) name += " " + lastName;
        return name;
	}
	
	//For adding recipients address for each order 
	@Transient
	public String getRecipientAddress() {
		String address = addressLine1;
		
		if (addressLine2 != null && !addressLine2.isEmpty()) address += ", " + addressLine2;
		
		if(!city.isEmpty()) address += ", " + city;
		
		if (state != null && !state.isEmpty()) address += ", " + state;
		
		address += ", " + country;
		
		if (postalCode.isEmpty()) address += ". " + postalCode;
		
		return address;
	}
}
