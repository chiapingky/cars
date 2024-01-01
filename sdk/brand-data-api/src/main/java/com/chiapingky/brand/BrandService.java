package com.chiapingky.brand;

public interface BrandService {
    Brand getBrandByName(String name);
    Brand insertBrand(Brand brand);
    Brand deleteBrand(Brand brand);
}
