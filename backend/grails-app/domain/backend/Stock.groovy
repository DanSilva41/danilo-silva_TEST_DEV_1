package backend

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.time.LocalDate

@ToString(includeNames = true)
@EqualsAndHashCode
class Stock {

    BigDecimal price
    LocalDate priceDate

    static constraints = {
        price nullable: false
        priceDate nullable: false
    }

    String toString() {
        price
        priceDate
    }
}
