package backend

import grails.gorm.transactions.Transactional

@Transactional
class StatisticsService {

    double average(List<BigDecimal> values) {
        if (Objects.isNull(values) || values.isEmpty())
            return 0
        return values.stream()
                .mapToDouble({ v -> v.doubleValue() })
                .average().orElse(0)
    }

    double standartDeviation(List<BigDecimal> values) {
        double average = this.average(values)
        if (Objects.isNull(values) || values.isEmpty() || average == 0)
            return 0

        double standartDeviation = 0
        values.forEach({ n ->
            standartDeviation += Math.pow(n - average, 2)
        })

        return Math.sqrt(standartDeviation / values.size())
    }
}
