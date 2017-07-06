package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.BaggageDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.order.Baggage;

/**
 * Service for interact with DAO layer interface BaggageDao
 */
public class BaggageService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static BaggageDao baggageDao = daoFactory.createBaggageDao();

    private BaggageService() {
    }

    private static class BaggageServiceHolder {
        private static final BaggageService instance = new BaggageService();
    }

    public static BaggageService getInstance() {
        return BaggageServiceHolder.instance;
    }
}
