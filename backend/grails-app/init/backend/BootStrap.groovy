package backend

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.time.LocalDateTime

@Slf4j
@CompileStatic
class BootStrap {

    CompanyService companyService
    StockService stockService
    BusinessService businessService

    def init = { servletContext ->
        log.println(LocalDateTime.now().toString() + " INFO --- " + BootStrap.getName() + "\t: initializing and grafting data")

        List<Company> companies = insertMockCompanies()
        for (Company c : companies)
            this.generateStockForCompany(c)

        log.println(LocalDateTime.now().toString() + " INFO --- " + BootStrap.getName() + "\t: data initializing finished")
    }

    def destroy = {
    }

    /**
     * Create and persist 3 companies in database
     * @return List<Company>
     */
    private List<Company> insertMockCompanies() {
        def listOfCompanies = new ArrayList<Company>()
        listOfCompanies.add(new Company(name: 'FORD', segment: 'vehicles'))
        listOfCompanies.add(new Company(name: 'NITRYX', segment: 'AI'))
        listOfCompanies.add(new Company(name: 'AMAZON', segment: 'cloud computing'))

        for (Company c : listOfCompanies)
            companyService.save(c)
        listOfCompanies
    }

    /**
     * Generate list of quotes for a company, based on the last 30 days
     * @param company
     */
    private void generateStockForCompany(Company company) {
        List<Stock> stocks = []
        final LocalDateTime actualDateHour = LocalDateTime.now()

        // 30 days ago
        LocalDateTime startHourDay = this.getDayAndHourAgo(actualDateHour, 1)

        LocalDateTime endBusinessHoursOfDay
        while (startHourDay <= actualDateHour) {
            if (businessService.isBusinessDay(startHourDay)) {
                endBusinessHoursOfDay = startHourDay.toLocalDate().atTime(businessService.endBusinessHour)
                while (startHourDay <= endBusinessHoursOfDay) {
                    this.addStockInCompany(stocks, startHourDay, company)
                    startHourDay = startHourDay.plusMinutes(1)
                }
            }
            startHourDay = startHourDay.toLocalDate().plusDays(1).atTime(businessService.startBusinessHour)
        }

        stockService.saveAll(stocks)
    }

    /**
     * Get day and hour ago of dateTime
     * @param actualDateHour : date by param
     * @param daysToSubtract : amount of days to subtract of date by param
     * @return LocalDateTime
     */
    private LocalDateTime getDayAndHourAgo(LocalDateTime actualDateHour, int daysToSubtract) {
        LocalDateTime.of(actualDateHour.toLocalDate().minusDays(daysToSubtract), businessService.startBusinessHour)
    }

    /**
     * Create a new stock for specific company with random price
     * @param stocks : list of stocks to be fed
     * @param stockTime : stock time
     * @param company : company to be linked
     *
     */
    private void addStockInCompany(List<Stock> stocks, LocalDateTime priceDate, Company company) {
        BigDecimal randomPrice = this.generateRandomPriceUntil(10000)
        stocks.add(new Stock(price: randomPrice, priceDate: priceDate, company: company))
    }

    /**
     * Generates random number from 0 until
     * @param maxRange : max number for random number generation
     * @return BigDecimal
     */
    private BigDecimal generateRandomPriceUntil(int maxRange) {
        new BigDecimal(BigInteger.valueOf(new Random().nextInt(maxRange)), 2)
    }

}
