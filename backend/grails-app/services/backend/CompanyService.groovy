package backend

import grails.gorm.services.Service

@Service(Company)
interface CompanyService {

    Company save(Company company)

}
