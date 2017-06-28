package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.BaggageDao;
import com.kushmiruk.model.entity.order.Baggage;

public class BaggageService extends GenericService<Baggage> {

    private static BaggageDao baggageDao = daoFactory.createBaggageDao();

    private BaggageService() {
        super(baggageDao);
    }

    private static class BaggageServiceHolder {
        private static final BaggageService instance = new BaggageService();
    }

    public static BaggageService getInstance() {
        return BaggageServiceHolder.instance;
    }
}
