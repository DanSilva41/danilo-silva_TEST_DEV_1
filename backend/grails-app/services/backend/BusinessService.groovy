package backend

import grails.gorm.transactions.Transactional

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

@Transactional
class BusinessService {

    private final Set<DayOfWeek> businessDays = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
    public final LocalTime startBusinessHour = LocalTime.of(10, 0)
    public final LocalTime endBusinessHour = LocalTime.of(18, 0)

    boolean isBusinessDay(LocalDateTime date) {
        businessDays.contains(date.dayOfWeek)
    }
}
