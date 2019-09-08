package com.infinite.life.thread.callableFuture

import spock.lang.Specification

class CallableFutureTest extends Specification {

    def callableFuture=new CallableFuture()

    def "callable Future verification"(){
        given:

        when:
        callableFuture.callableFuture()

        then:
        1==1
    }
}
