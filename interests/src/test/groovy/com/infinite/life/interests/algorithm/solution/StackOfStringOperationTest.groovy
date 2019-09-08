package com.infinite.life.interests.algorithm.solution

import spock.lang.Specification

class StackOfStringOperationTest extends Specification {
    def clazz=new StackOfStringOperation();

    void setup() {

    }

    void cleanup() {
    }

    def "the class retrun result verification"() {
        given:
        def caseOne="3+6*2-5*(9/(5*(4-2)-7)+(5/2-1))+8"
        def caseTwo="1*2*(5/4+3)-5"

        expect:
        clazz.deal(caseOne)== 3
        clazz.deal(caseTwo)==3
    }

    def "the class retrun result verification using where"() {
        given:

        expect:
        clazz.deal(a)== b

        where:
        a||b
        "3+6*2-5*(9/(5*(4-2)-7)+(5/2-1))+8"||3
        "1*2*(5/4+3)-5"||3
    }
}
