package com.example.dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public enum Country {
    USA("USA"),
    Germany("Germany");
    private final String name;
    private List<Company> companyList;


    Country(String name) {
        this.name = name;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }
    public static Optional<Country> asBrand(Brand brand){
        for(Country country:Country.values()){
            var findCountry=country.getCompanyList().stream().map(Company::getBrand).filter(brand1 -> brand1==brand).findFirst().orElse(null);
            if(findCountry!=null){
                return Optional.of(country);
            }

        }
        return Optional.empty();
    }

    public String getName() {
        return name;
    }
}
