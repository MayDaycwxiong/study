package com.infinite.life.interests.spock

import spock.lang.Specification


class SumTest extends Specification {
    def sum = new Sum()

    def "calculate the sum of param1+param2"() {
        expect:
        sum.sum(1, 1) == 2
    }

}
