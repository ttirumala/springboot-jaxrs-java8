package com.account.jaxrs.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.NumberFormat;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "customerName")
    @JsonProperty("customerName")
    private String customerName;
    @XmlElement(name = "currency")
    @JsonProperty("currency")
    private String currency;
    @XmlElement(name = "amount")
    @JsonProperty("amount")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal amount;
    @XmlAttribute(name = "id")
    @JsonProperty("id")
    private int id;
    @XmlAttribute(name = "uri")
    @JsonProperty("uri")
    private String uri;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
