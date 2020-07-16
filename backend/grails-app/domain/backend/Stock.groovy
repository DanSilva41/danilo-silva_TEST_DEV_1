package backend

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.time.LocalDateTime

@ToString
@EqualsAndHashCode
class Stock {

    BigDecimal price
    LocalDateTime priceDate

    static belongsTo = [company: Company]

    static constraints = {
        price nullable: false
        priceDate nullable: false
    }
}
