package com.app.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetails implements Serializable {

    private static final long serialVersionUID = -3107573704814973121L;

    private String username;
    private String email;
    private String brand;
    @JsonProperty("publiccustomerid")
    private Long customerId;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("phonenumber")
    private String phoneNumber;
    @JsonProperty("postalcode")
    private String postalCode;
    private String city;
    private String isoCountryCode;
    private Locale locale;
    private Date birthDate;
    private String jurisdiction;
    private String ssn;

    protected CustomerDetails() {
    }

    public String getUsername() {
        return username;
    }

    public String getBrand() {
        return brand;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmail() {
        return email;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class SearchCustomerBuilder {
        private final CustomerDetails customer = new CustomerDetails();

        protected CustomerDetails build(CustomerDetails newCustomer) {
            newCustomer.username = customer.username;
            newCustomer.brand = customer.brand;
            newCustomer.customerId = customer.customerId;
            newCustomer.firstName = customer.firstName;
            newCustomer.lastName = customer.lastName;
            newCustomer.phoneNumber = customer.phoneNumber;
            newCustomer.postalCode = customer.postalCode;
            newCustomer.city = customer.city;
            newCustomer.isoCountryCode = customer.isoCountryCode;
            newCustomer.locale = customer.locale;
            newCustomer.birthDate = customer.birthDate;
            newCustomer.jurisdiction = customer.jurisdiction;
            newCustomer.ssn = customer.ssn;
            newCustomer.email = customer.email;
            return newCustomer;
        }

        public CustomerDetails build() {
            return build(new CustomerDetails());
        }

        public SearchCustomerBuilder username(String username) {
            customer.username = username;
            return this;
        }

        public SearchCustomerBuilder email(String email) {
            customer.email = email;
            return this;
        }

        public SearchCustomerBuilder brand(String brand) {
            customer.brand = brand;
            return this;
        }

        public SearchCustomerBuilder customerId(Long customerId) {
            customer.customerId = customerId;
            return this;
        }

        public SearchCustomerBuilder firstName(String firstName) {
            customer.firstName = firstName;
            return this;
        }

        public SearchCustomerBuilder lastName(String lastName) {
            customer.lastName = lastName;
            return this;
        }

        public SearchCustomerBuilder phoneNumber(String phoneNumber) {
            customer.phoneNumber = phoneNumber;
            return this;
        }

        public SearchCustomerBuilder postalCode(String postalCode) {
            customer.postalCode = postalCode;
            return this;
        }

        public SearchCustomerBuilder city(String city) {
            customer.city = city;
            return this;
        }

        public SearchCustomerBuilder isoCountryCode(String isoCountryCode) {
            customer.isoCountryCode = isoCountryCode;
            return this;
        }

        public SearchCustomerBuilder locale(Locale locale) {
            customer.locale = locale;
            return this;
        }

        public SearchCustomerBuilder birthDate(Date birthDate) {
            customer.birthDate = birthDate;
            return this;
        }

        public SearchCustomerBuilder jurisdiction(String jurisdiction) {
            customer.jurisdiction = jurisdiction;
            return this;
        }

        public SearchCustomerBuilder ssn(String ssn) {
            customer.ssn = ssn;
            return this;
        }
    }

    @Override
    public String toString() {
        return username + "@" + brand;
    }
}
