package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.ExtraPriceDao;
import com.kushmiruk.model.entity.order.ExtraPrice;

public class ExtraPriceService extends GenericService<ExtraPrice> {
    private static ExtraPriceDao extraPriceDao = daoFactory.createExtraPriceDao();

    private ExtraPriceService() {
        super(extraPriceDao);
    }

    private static class ExtraPriceServiceHolder {
        private static final ExtraPriceService instance = new ExtraPriceService();
    }

    public static ExtraPriceService getInstance() {
        return ExtraPriceServiceHolder.instance;
    }
}
