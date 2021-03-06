package com.fjroa.advent2019

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.awt.Point
import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.system.measureTimeMillis

@Component
class AdventOfCode2019 : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        println(measureTimeMillis {
            println("Result of day 1 : ${day1(ClassPathResource("day1.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 1.2 : ${day1_2(ClassPathResource("day1.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 2 : ${day2(ClassPathResource("day2.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 2.2 : ${day2_2(ClassPathResource("day2.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 3 : ${day3(ClassPathResource("day3.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 3.2 : ${day3_2(ClassPathResource("day3.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 4 : ${day4(178416, 676461)}")
        })
        println(measureTimeMillis {
            println("Result of day 4.2 : ${day4(178416, 676461, true)}")
        })
        println(measureTimeMillis {
           // println("Result of day 5 : ${day5(ClassPathResource("day5.txt").file)}")
        })
        println(measureTimeMillis {
            println("Result of day 5.2 : ${day5_2(ClassPathResource("day5.txt").file)}")
        })
    }

    private fun day5(file: File): Int {
        val values = file.readText().trim().split(",").map { it.toInt() }.toIntArray()
        return intCodeProgram(values,0, 0, 1)
    }

    private fun day5_2(file: File): Int {
        val values = file.readText().trim().split(",").map { it.toInt() }.toIntArray()
        return intCodeProgram(values,0, 0, 5)
    }

    private fun day4(lower: Int, upper: Int, advanced: Boolean = false): Int {
        var counter = 0
        for (i in lower..upper) {
            if (validatePassword(i.toString(), advanced))
                counter++
        }
        return counter
    }

    fun validatePassword(password: String, advanced: Boolean = false): Boolean {
        val digits = password.map { it.toString().toInt() }
        var doubleCondition = false
        var i = 0
        while (i < digits.size-1) {
            if (digits[i] > digits[i+1])
                return false
            if (digits[i] == digits[i+1]) {
                if (advanced) {
                    var counter = 0
                    while (digits[i] == digits[i + 1]) {
                        counter++
                        if (++i == digits.size - 1) break;
                    }
                    if (counter == 1) doubleCondition = true
                } else {
                    doubleCondition = true
                    i++
                }
            } else {
                i++
            }
        }
        return doubleCondition
    }

    private fun day1(file: File): Int {
        return file.readLines().sumBy{ calcFuel(it.toInt()) }
    }

    private fun day1_2(file: File): Int {
        var result = 0;
        file.forEachLine { var fuel = it.toInt()
            while (fuel > 0) {
                fuel = calcFuel(fuel)
                if (fuel >0) result += fuel
            }}
        return result;
    }

    private fun day2(file: File): Int {
        val values = file.readText().trim().split(",").map { it.toInt() }.toIntArray()
        return intCodeProgram(values,12, 2)
    }

    private fun day2_2(file: File): Int {
        val values = file.readText().trim().split(",").map { it.toInt() }.toIntArray()
        for (noun in 0..99)
            for (verb in 0..99) {
                if ( intCodeProgram(values.clone(), noun, verb) == 19690720)
                    return noun * 100 + verb
            }
        return -1
    }

    private fun day3(file: File): Int {
        return findWiresIntersections(file.readLines().toTypedArray())
    }

    private fun day3_2(file: File): Int {
        return findMinimizedWiresIntersections(file.readLines().toTypedArray())
    }

    internal fun calcFuel(mass: Int) = floor(mass.div(3f)).toInt().minus(2)

    internal fun intCodeProgram(i: IntArray, noun: Int, verb: Int, input: Int = -1): Int {
        var pos = 0
        if (input == -1) {
            i[1] = noun
            i[2] = verb
        }
        var lastCalculation = 0
        var output = 0
        while (i[pos] != 99) {
            var opCode = i[pos]
            var firstParamImmediateMode = false
            var secondParamImmediateMode = false
            if (opCode > 100) {
                val reverseOpCode = opCode.toString().reversed()
                firstParamImmediateMode = reverseOpCode.substring(2,3) == "1"
                if (opCode > 1000) {
                    secondParamImmediateMode = reverseOpCode.substring(3, 4) == "1"
                }
                opCode = reverseOpCode.substring(0,1).toInt()
            }
            when {
                opCode == 1 -> {lastCalculation = getValueIntCodeProgram(i,pos+1,firstParamImmediateMode) + getValueIntCodeProgram(i,pos+2,secondParamImmediateMode); i[i[pos+3]] = lastCalculation; pos +=4}
                opCode == 2 -> {lastCalculation = getValueIntCodeProgram(i,pos+1,firstParamImmediateMode) * getValueIntCodeProgram(i,pos+2,secondParamImmediateMode); i[i[pos+3]] = lastCalculation; pos +=4}
                opCode == 3 -> {i[i[pos+1]] = input; pos +=2}
                opCode == 4 -> {output = getValueIntCodeProgram(i,pos+1,firstParamImmediateMode); pos +=2}
                opCode == 5 -> {if(getValueIntCodeProgram(i,pos+1,firstParamImmediateMode) != 0) pos = getValueIntCodeProgram(i,pos+2,secondParamImmediateMode) else pos +=3}
                opCode == 6 -> {if(getValueIntCodeProgram(i,pos+1,firstParamImmediateMode) == 0) pos = getValueIntCodeProgram(i,pos+2,secondParamImmediateMode) else pos +=3}
                opCode == 7 -> {if(getValueIntCodeProgram(i,pos+1,firstParamImmediateMode) < getValueIntCodeProgram(i,pos+2,secondParamImmediateMode)) i[i[pos+3]] = 1 else i[i[pos+3]]=0; pos +=4}
                opCode == 8 -> {if(getValueIntCodeProgram(i,pos+1,firstParamImmediateMode) == getValueIntCodeProgram(i,pos+2,secondParamImmediateMode)) i[i[pos+3]] = 1 else i[i[pos+3]]=0; pos +=4}
                else -> return -1
            }
            if (output != 0) {
                if (i[pos] != 99)
                    return -1
                else
                    return output
            }
            //System.out.println(pos)
        }
        if (input != -1) {
            return output
        }
        return lastCalculation
    }

    private fun getValueIntCodeProgram(i: IntArray, pos: Int, immediateMode: Boolean): Int {
        if (immediateMode)
            return i[pos]
        return i[i[pos]]
    }


    fun findWiresIntersections(wires: Array<String>): Int {
        val n = 20000
        val canvas = Array(n) {IntArray(n)}
        val crossingPoints = arrayListOf<Point>()
        for (wire in wires) {
            val wirePoints = hashSetOf<Point>()
            var p = Point(n/2,n/2)
            for (inst in wire.split(",")) {
                val dir = inst.substring(0,1)
                val distance = inst.substring(1).toInt()
                for (i in 1..distance) {
                    when {
                        dir == "R" -> p.x += 1
                        dir == "L" -> p.x -= 1
                        dir == "U" -> p.y += 1
                        dir == "D" -> p.y -= 1
                    }
                    if (wirePoints.add(p)) {
                        if (canvas[p.x][p.y] > 0) {
                            crossingPoints.add(Point(p.x - n / 2, p.y - n / 2))
                        }
                        canvas[p.x][p.y]++
                    }
                }
            }
        }

        var minDistance = Int.MAX_VALUE
        for (point in crossingPoints) {
            val manhattanDistance = point.x.absoluteValue + point.y.absoluteValue
            if ( manhattanDistance < minDistance) {
                minDistance = manhattanDistance
            }
        }

        return minDistance
    }

    fun findMinimizedWiresIntersections(wires: Array<String>): Int {
        val n = 20000
        val canvas = Array(n) {IntArray(n)}
        val crossingPoints = arrayListOf<Point>()
        for (wire in wires) {
            val wirePoints = hashSetOf<Point>()
            var p = Point(n/2,n/2)
            var steps = 0
            for (inst in wire.split(",")) {
                val dir = inst.substring(0,1)
                val distance = inst.substring(1).toInt()
                for (i in 1..distance) {
                    when {
                        dir == "R" -> p.x += 1
                        dir == "L" -> p.x -= 1
                        dir == "U" -> p.y += 1
                        dir == "D" -> p.y -= 1
                    }
                    steps++
                    if (wirePoints.add(p)) {
                        if (canvas[p.x][p.y] > 0) {
                            crossingPoints.add(Point(p))
                        }
                        canvas[p.x][p.y]+=steps
                    }
                }
            }
        }

        var minDistance = Int.MAX_VALUE
        for (point in crossingPoints) {
            var optimalDistance = canvas[point.x][point.y]
            if ( optimalDistance < minDistance) {
                minDistance = optimalDistance
            }
        }

        return minDistance
    }
}