package com.samsunglife.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.samsunglife.sample.model.City;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CityHashMapMapper {
    City selectCityById(Long id);
    List<City> selectAllCity();
    void insertCity(HashMap<String, String> city);
}