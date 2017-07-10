package com.kushmiruk.service;

/**
 * Service for interact with DAO layer interface ExtraPriceDao
 */
public class ExtraPriceService {

    private ExtraPriceService() {
    }

    private static class ExtraPriceServiceHolder {
        private static final ExtraPriceService instance = new ExtraPriceService();
    }

    public static ExtraPriceService getInstance() {
        return ExtraPriceServiceHolder.instance;
    }
}
