package backend

import grails.gorm.transactions.Transactional

@Transactional
class StockService {

    List<Stock> saveAll(List<Stock> stocks) {
        Stock.saveAll(stocks)
    }
}
