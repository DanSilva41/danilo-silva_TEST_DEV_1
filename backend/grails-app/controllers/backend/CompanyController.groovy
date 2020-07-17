package backend

import grails.converters.JSON


class CompanyController {

    CompanyService companyService

    static responseFormats = ['json']
    static allowedMethods = [read: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    def index() {}

    def listCompanies() {
        def listCompanies = companyService.listCompanies()
        def respostListJson = render listCompanies as JSON

        respond respostListJson
    }
}
