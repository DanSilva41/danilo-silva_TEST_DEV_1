package backend

import java.time.LocalDate

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
