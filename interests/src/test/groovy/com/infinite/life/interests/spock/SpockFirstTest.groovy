package com.infinite.life.interests.spock

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Blocks(setup(given),when,then,expect,cleanup,where)
 */
class SpockFirstTest extends Specification {
    // #######################################################
    // setup blocks
    // 特征方法
    def "adding an element leads to size increase"() {
        // 初始化工作
        setup: "a new stack instance is created"
        def stack = new Stack()
        def elem = "42"
        // 特征下的动作
        when:
        stack.push elem
        // 验证when块触发的结果的任何表达式
        then:
        1 * stack.size() == 1
        println(stack.size())
    }

    // #######################################################
    // when and then blocks
    def "operatin of stack"() {
        setup: "init a stack and an element"
        def stack = new Stack()
        def element = "push me"

        when:
        stack.push(element)

        then:
        stack.empty() == false
        stack.size() == 1
        stack.peek() == element
    }

    // #######################################################
    // assert
    def "assert stack empty verification"() {
        setup:
        def stack = new Stack<>()
        assert stack.empty
    }

    // #######################################################
    // 验证抛出异常，并获取异常
    def "stack pop exception verification"() {
        setup:
        def stack = new Stack()

        when:
        stack.pop()

        then:
        def e = thrown(EmptyStackException)
        e.cause == null
        stack.empty
    }

    // 验证没有异常抛出
    def "hashMap accepts null key verification"() {
        setup:
        def map = new HashMap()

        when:
        map.put(null, "element")

        then:
        notThrown(NullPointerException)
    }

    // #######################################################
    // expect ===>when + then
    // when + then
    def "return the most biggest values of param1 and param2 using when then"() {
        setup:

        when:
        def x = Math.max(1, 2)

        then:
        x == 2
    }
    // expect
    def "return the most biggest values of param1 and param2 using expect"() {
        setup:

        expect:
        Math.max(1, 2) == 2
    }

    // #######################################################
    // where blocks
    // expect
    def "return the max num of two numbers using expect"() {
        expect:
        Math.max(1, 3) == 3
        Math.max(7, 4) == 7
        Math.max(0, 0) == 0
    }
    // where for i=>3 take=333ms
    def "return the max num of two numbers using where third"() {
        setup:
        println("execute")

        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7
        0 | 0 || 0
    }
    // where 方法执行3次 take=92ms
    @Unroll
    def "return the max num of two numbers using where once"() {
        setup:
        println("execute")

        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7
        0 | 0 || 0
    }

    def "return the max num of two numbers using where another strategy"() {
        given:
        println("execute")

        expect:

        where:
        a | _
        3 | _
        7 | _
        0 | _
        b << [5, 0, 0]
        c = a > b ? a : b
    }
    // #######################################################
}
