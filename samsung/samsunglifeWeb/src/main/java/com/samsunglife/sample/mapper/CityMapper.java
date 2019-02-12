package com.samsunglife.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.samsunglife.sample.model.City;

import java.util.List;

@Mapper
public interface CityMapper {
    City selectCityById(Long id);
    List<City> selectAllCity();
    void insertCity(City city);
}