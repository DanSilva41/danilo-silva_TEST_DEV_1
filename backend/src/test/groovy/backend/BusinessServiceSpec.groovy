package backend


import grails.testing.services.ServiceUnitTest

import java.time.LocalDateTime

class BusinessServiceSpec implements ServiceUnitTest<BusinessService> {


    def setup() {
    }

    def cleanup() {
    }

    void "should validate a business day"() {
        expect: "is business day"
        service.isBusinessDay(LocalDateTime.now())
    }
}
