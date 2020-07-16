package backend

import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Transactional
class StockService {

    List<Stock> saveAll(List<Stock> stocks) {
        Stock.saveAll(stocks)
    }

    List<Stock> getStocks(String companyName, int numbersOfHoursUntilNow) {
        def initExecution = System.currentTimeMillis()

        List<Stock> stocksFounds = this.getStockByCompanyAndPriceDate(companyName, numbersOfHoursUntilNow)

        log.println("\nALL QUOTES RECOVERED")
        stocksFounds.each { log.println(it.toString()) }

        def endExecution = System.currentTimeMillis()

        this.printOthers(endExecution, initExecution, stocksFounds)
        stocksFounds

    }

    private List<Stock> getStockByCompanyAndPriceDate(String companyName, int numbersOfHoursUntilNow) {
        Company companyFound = Company.findByName(companyName)
        LocalDateTime dateBeforeUntilNow = LocalDateTime.now().minusHours(numbersOfHoursUntilNow)
        Stock.findAllByCompanyAndPriceDateGreaterThanEquals(companyFound, dateBeforeUntilNow, [sort: 'priceDate', order: 'asc'])
    }

    private void printOthers(long endExecution, long initExecution, List<Stock> stocksFounds) {
        double seconds = (endExecution - initExecution) / 1000.0
        log.println("\nTOTAL EXECUTION TIME: " + seconds + " seconds")
        log.printf("TOTAL QUOTES RECOVERED: %d\n", stocksFounds.size())
    }
}
