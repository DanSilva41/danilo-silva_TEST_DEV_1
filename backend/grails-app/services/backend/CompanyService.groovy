package backend

import grails.gorm.transactions.Transactional

import java.util.stream.Collectors

@Transactional
class CompanyService {

    StockService stockService

    Company save(Company company) {
        company.save()
        company
    }

    def listCompanies() {
        List<Company> companies = Company.list()

        companies.parallelStream().map({ c ->
            [
                    id       : c.getId(),
                    name     : c.getName(),
                    segment  : c.getSegment(),
                    deviation: stockService.getStandardDeviationOfCompany(c)
            ]
        }).collect(Collectors.toList())
    }

}
