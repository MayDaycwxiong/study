package com.infinite.life.interests.baseInfo

import spock.lang.Specification

class ATest extends Specification {

    def "ClassLoadingOrder Test"(){
        given:
        def a=new A()
    }

    def "ClassLoadingOrder static Test"(){
        given:
        A.aTest()
    }
}
