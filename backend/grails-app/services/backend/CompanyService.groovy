package backend

import grails.gorm.services.Service

@Service(Company)
interface CompanyService {

    Company save(Company company)

    void delete(Serializable id)

    Company getById(Serializable id)

    List<Company> findAllBy(Map args)

    Long count()
}
