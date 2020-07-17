package backend

import grails.gorm.transactions.Transactional

import java.math.RoundingMode
import java.time.LocalDateTime
import java.util.stream.Collectors

@Transactional
class StockService {

    def statisticsService

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

    BigDecimal getStandardDeviation(Company company) {

        List<Stock> stocksOfCompany = Stock.findAllByCompany(company)
        List<BigDecimal> pricesOfStocks = stocksOfCompany.stream().map({ s -> s.price }).collect(Collectors.toList());

        double standartDeviation = statisticsService.standartDeviation(pricesOfStocks)
        new BigDecimal(standartDeviation).setScale(2, RoundingMode.HALF_UP)
    }

}
