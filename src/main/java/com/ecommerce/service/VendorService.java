package com.ecommerce.service;

import com.ecommerce.model.Vendor;
import com.ecommerce.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // Task - Inserting a new vendor
    public void insertVendor(Vendor vendor) {
        vendorRepository.insertVendor(vendor);
    }
}
