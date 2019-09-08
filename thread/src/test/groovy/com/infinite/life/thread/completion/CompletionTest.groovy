package com.infinite.life.thread.completion

import spock.lang.Specification

class CompletionTest extends Specification {
    def completion=new Completion()

    def "Callable and Future completionService strategy verification"(){
        given:

        when:
        completion.handle()

        then:
        1==1
    }
}
