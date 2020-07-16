package backend

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString()
@EqualsAndHashCode(excludes = ['segment'])
class Company {

    String name
    String segment
    static hasMany = [stocks: Stock]

    static constraints = {
        name blank: false, unique: true, size: 2..60
        segment blank: false, size: 2..60
    }
}
