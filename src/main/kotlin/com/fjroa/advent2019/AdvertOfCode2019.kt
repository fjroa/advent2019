package com.fjroa.advent2019

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.File
import kotlin.math.floor

@Component
class AdventOfCode2019 : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        println("Result of day 1 : ${day1(ClassPathResource("day1.txt").file)}")
        println("Result of day 1.2 : ${day1_2(ClassPathResource("day1.txt").file)}")
        println("Result of day 2 : ${day2(ClassPathResource("day2.txt").file)}")
        println("Result of day 2.2 : ${day2_2(ClassPathResource("day2.txt").file)}")
    }

    fun day2(file: File): Int {
        val values = file.readText().trim().split(",").map { it.toInt() }.toIntArray()
        return intCodeProgram(values,12, 2)
    }

    fun day2_2(file: File): Int {
        val values = file.readText().trim().split(",").map { it.toInt() }.toIntArray()
        for (noun in 0..99)
            for (verb in 0..99) {
                if ( intCodeProgram(values.clone(), noun, verb) == 19690720)
                    return noun * 100 + verb
            }
        return -1
    }

    fun day1(file: File): Int {
        return file.readLines().sumBy{ calcFuel(it.toInt()) }
    }

    fun day1_2(file: File): Int {
        var result = 0;
        file.forEachLine { var fuel = it.toInt()
            while (fuel > 0) {
                fuel = calcFuel(fuel)
                if (fuel >0) result += fuel
            }}
        return result;
    }

    internal fun calcFuel(mass: Int) = floor(mass.div(3f)).toInt().minus(2)

    fun intCodeProgram(i: IntArray, noun: Int, verb: Int): Int {
        var pos = 0
        i[1] = noun
        i[2] = verb
        var lastCalculation = 0
        while (i[pos] != 99) {
            when {
                i[pos] == 1 -> lastCalculation = i[i[pos+1]] + i[i[pos+2]]
                i[pos] == 2 -> lastCalculation = i[i[pos+1]] * i[i[pos+2]]
                else -> return -1
            }
            i[i[pos+3]] = lastCalculation
            pos += 4
        }
        return lastCalculation
    }

}