package com.fjroa.advent2019

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AdventOfCode2019Test {

    @InjectMocks
    lateinit var advent: AdventOfCode2019

    @Test
    fun intCodeProgram() {
        assertEquals(3500, advent.intCodeProgram(intArrayOf(1,9,10,3,2,3,11,0,99,30,40,50),9,10))
        assertEquals(2, advent.intCodeProgram(intArrayOf(1,0,0,0,99),0,0))
        assertEquals(6, advent.intCodeProgram(intArrayOf(2,3,0,3,99),3,0))
        assertEquals(9801, advent.intCodeProgram(intArrayOf(2,4,4,5,99,0),4,4))
        assertEquals(30, advent.intCodeProgram(intArrayOf(1,1,1,4,99,5,6,0,99),1,1))
        assertEquals(500, advent.intCodeProgram(intArrayOf(3,0,4,0,99),0,0, 500))
        assertEquals(99, advent.intCodeProgram(intArrayOf(1002,4,3,4,33),4,3))
        assertEquals(99, advent.intCodeProgram(intArrayOf(1101,100,-1,4,0),100,-1))
        assertEquals(1, advent.intCodeProgram(intArrayOf(3,9,8,9,10,9,4,9,99,-1,8),0,0, 8))
        assertEquals(0, advent.intCodeProgram(intArrayOf(3,9,8,9,10,9,4,9,99,-1,8),0,0, 7))
        assertEquals(1, advent.intCodeProgram(intArrayOf(3,3,1108,-1,8,3,4,3,99),0,0, 8))
        assertEquals(0, advent.intCodeProgram(intArrayOf(3,3,1108,-1,8,3,4,3,99),0,0, 7))
        assertEquals(1, advent.intCodeProgram(intArrayOf(3,9,7,9,10,9,4,9,99,-1,8),0,0, 7))
        assertEquals(0, advent.intCodeProgram(intArrayOf(3,9,7,9,10,9,4,9,99,-1,8),0,0, 8))
        assertEquals(1, advent.intCodeProgram(intArrayOf(3,3,1107,-1,8,3,4,3,99),0,0, 7))
        assertEquals(0, advent.intCodeProgram(intArrayOf(3,3,1107,-1,8,3,4,3,99),0,0, 8))
        assertEquals(1, advent.intCodeProgram(intArrayOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9),0,0, 7))
        assertEquals(0, advent.intCodeProgram(intArrayOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9),0,0, 0))
        assertEquals(1, advent.intCodeProgram(intArrayOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1),0,0, 7))
        assertEquals(0, advent.intCodeProgram(intArrayOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1),0,0, 0))
    }

    @Test
    fun wiresIntersection() {
        assertEquals(6, advent.findWiresIntersections(arrayOf("R8,U5,L5,D3","U7,R6,D4,L4")))
        assertEquals(159, advent.findWiresIntersections(arrayOf("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83")))
        assertEquals(135, advent.findWiresIntersections(arrayOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")))
    }

    @Test
    fun minimizedWiresIntersection() {
        assertEquals(30, advent.findMinimizedWiresIntersections(arrayOf("R8,U5,L5,D3","U7,R6,D4,L4")))
        assertEquals(610, advent.findMinimizedWiresIntersections(arrayOf("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83")))
        assertEquals(410, advent.findMinimizedWiresIntersections(arrayOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")))
    }

    @Test
    fun validatePassword() {
        assertTrue(advent.validatePassword("111111"))
        assertFalse(advent.validatePassword("223450"))
        assertFalse(advent.validatePassword("123789"))
        assertTrue(advent.validatePassword("112233", true))
        assertFalse(advent.validatePassword("123444", true))
        assertTrue(advent.validatePassword("111122", true))
    }


}