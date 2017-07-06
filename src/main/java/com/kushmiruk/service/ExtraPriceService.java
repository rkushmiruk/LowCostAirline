package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.ExtraPriceDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.order.ExtraPrice;

/**
 * Service for interact with DAO layer interface ExtraPriceDao
 */
public class ExtraPriceService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static ExtraPriceDao extraPriceDao = daoFactory.createExtraPriceDao();

    private ExtraPriceService() {
    }

    private static class ExtraPriceServiceHolder {
        private static final ExtraPriceService instance = new ExtraPriceService();
    }

    public static ExtraPriceService getInstance() {
        return ExtraPriceServiceHolder.instance;
    }
}
