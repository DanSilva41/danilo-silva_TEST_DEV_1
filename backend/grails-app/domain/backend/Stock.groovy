package backend

import groovy.transform.EqualsAndHashCode

import java.time.LocalDateTime

@EqualsAndHashCode
class Stock {

    BigDecimal price
    LocalDateTime priceDate

    static belongsTo = [company: Company]

    static constraints = {
        price nullable: false
        priceDate nullable: false
    }

    @Override
    String toString() {
        return "Stock {" +
                "company = " + company +
                ", price=" + price +
                ", priceDate=" + priceDate +
                '}'
    }
}
