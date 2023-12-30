package com.chiapingky.cars.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand getBrandByName(String name) {
        return brandRepository.getBrandByName(name).orElse(null);
    }

    public Brand insertBrand(Brand brand) {
        if (getBrandByName(brand.getName()) != null) {
            throw new IllegalStateException("Brand already exist");
        }
        brandRepository.save(brand);
        return getBrandByName(brand.getName());
    }

    public Brand deleteBrand(Brand brand) {
        Brand brandExist = getBrandByName(brand.getName());
        if (brandExist != null) {
            brandRepository.delete(brandExist);
            return brandExist;
        } else {
            throw new IllegalStateException("Brand does not exist");
        }
    }
}
