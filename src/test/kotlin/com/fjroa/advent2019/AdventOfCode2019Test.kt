package com.fjroa.advent2019

import org.junit.Assert.assertEquals
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
    }
}