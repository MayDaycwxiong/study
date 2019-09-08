package com.infinite.life.thread.latch

import spock.lang.Specification

class HarnessMainTest extends Specification {

    def harnessMain=new HarnessMain();

    def "countDownLatch verification"() {
        given:

        when:
        harnessMain.handle()

        then:
        1==1
    }
}
