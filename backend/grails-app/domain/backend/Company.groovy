package backend

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Company {

    String name
    String segment

    static constraints = {
        name blank: false, unique: true, size: 2..60
        segment blank: false, size: 2..60
    }

    String toString() {
        name
        segment
    }
}
