package com.kushmiruk.service;

/**
 * Service for interact with DAO layer interface BaggageDao
 */
public class BaggageService {


    private BaggageService() {
    }

    private static class BaggageServiceHolder {
        private static final BaggageService instance = new BaggageService();
    }

    public static BaggageService getInstance() {
        return BaggageServiceHolder.instance;
    }
}
