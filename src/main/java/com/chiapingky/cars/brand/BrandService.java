package com.chiapingky.cars.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Brand getBrandByName(String name) {
        return brandRepository.getBrandByName(name).orElse(null);
    }

    public Brand insertBrand(Brand brand) {
        Optional<Brand> brandExist = brandRepository.getBrandByName(brand.getName());
        if (brandExist.isPresent()) {
            return null;
        }
        brandRepository.save(brand);
        return brandRepository.getBrandByName(brand.getName()).orElse(null);
    }

    public Brand deleteBrand(Brand brand) {
        Optional<Brand> brandExist = brandRepository.getBrandByName(brand.getName());
        Brand result = null;
        if (brandExist.isPresent()) {
            result = brandExist.get();
            brandRepository.delete(result);
        }
        return result;
    }
}
