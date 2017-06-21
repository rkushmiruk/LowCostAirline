package com.kushmiruk;

import com.kushmiruk.dao.daointerface.AirportDao;
import com.kushmiruk.dao.factory.MySqlDaoFactory;
import com.kushmiruk.dao.impl.util.InsertQueryBuilder;
import com.kushmiruk.dao.impl.util.QueryBuilderFactory;
import com.kushmiruk.dao.impl.util.SelectQueryBuilder;
import com.kushmiruk.model.entity.location.Airport;
import com.kushmiruk.model.entity.location.City;

import java.util.List;


public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        MySqlDaoFactory mySqlDaoFactory = MySqlDaoFactory.getInstance();
        AirportDao ins = mySqlDaoFactory.createAirportDao();
//
        Airport airport = ins.findById(1L).get();
        City city = new City();
        city.setId(2L);
        airport.setCity(city);

//        List<Airport> airports = ins.findAll();
//
//        System.out.println(airport);
//        System.out.println(airports);

//        QueryBuilderFactory factory = QueryBuilderFactory.getInstance();
//        InsertQueryBuilder builder = factory.createInsertQueryBuilder("Airport");
//
//        System.out.println(builder);

        System.out.println(ins.insert(airport));


    }
}
