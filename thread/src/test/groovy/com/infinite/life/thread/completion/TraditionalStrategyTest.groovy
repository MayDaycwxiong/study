package com.infinite.life.thread.completion

import spock.lang.Specification

class TraditionalStrategyTest extends Specification {
    def traditionalStrategy=new TraditionalStrategy();
    void setup() {
    }

    void cleanup() {
    }

    def "Callable and Future traditional strategy verification"() {
        given:

        when:
        traditionalStrategy.handle()

        then:
        1==1
    }
}
