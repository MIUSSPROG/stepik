import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min
import kotlin.reflect.typeOf
import kotlin.system.measureTimeMillis

fun fibo() {
    val data = readln().split(' ')
    val n = data[0].toLong()
    val m = data[1].toInt()
    var f0 = 0
    var f1 = 1
    var fn: Int = f0 + f1
    if (n >= 2) {
        for (i in 2..n) {
            fn = (f0 + f1) % m
            f0 = f1
            f1 = fn
        }
    }
    println(fn)
}

fun fiboBig() {
    val data = readln().split(' ')
    val mods = mutableListOf<Int>()
    val n = data[0].toLong()
    val m = data[1].toInt()
    var f0 = 0
    var f1 = 1
    var fn: Int = 1
    mods.add(0)
    mods.add(1)
    if (n >= 2) {
        for (i in 2..n) {
            fn = (f0 + f1) % m
            f0 = f1
            f1 = fn
            if (mods.last() == 0 && fn == 1) {
                mods.removeLast()
                val index = n % (i - 1)
                println(mods[index.toInt()])
                break
            } else if (mods.size >= n) {
                println(mods.last())
                break
            }
            mods.add(fn)
        }
    }
}

fun gcd(a: Int, b: Int) {
    if (a == b) {
        println(a)
    }
    if (a == 0 || b == 0) {
        print(a + b)
    } else if (a > b) {
        gcd(a % b, b)
    } else if (a < b) {
        gcd(a, b % a)
    }
}

fun fiboRec(n: Int, a: MutableList<BigInteger>, cache: MutableMap<Int, BigInteger>): BigInteger {
    a[0]++
    if (n < 2) {
        return BigInteger(n.toString())
    }
    if (cache.containsKey(n)) {
        return cache[n]!!
    }
    val res = fiboRec(n - 1, a, cache) + fiboRec(n - 2, a, cache)
    cache[n] = res
    return res
}

fun fiboRec2(n: Int, a: MutableList<BigInteger>, cache: MutableMap<Int, BigInteger>): BigInteger {
    a[0]++
    if (n < 2) {
        return BigInteger(n.toString())
    }
    return if (cache.containsKey(n)) {
        cache[n]!!
    } else {
        for (i in 2..n) {
            val res = fiboRec(i - 1, a, cache).add(fiboRec(i - 2, a, cache))
            cache[i] = res
        }
        cache[n]!!
    }
}

fun fiboLoop() {
    for (i in 0..100) {
        val cnt = mutableListOf<BigInteger>(BigInteger("0"))
        val cache = mutableMapOf<Int, BigInteger>()
        val time = measureTimeMillis {
            val value = fiboRec(i, cnt, cache)
            println("calls = ${cnt[0]}\nvalue = $value")
        }
        println("n = 1000 \ntime: ${time.toFloat() / 1000}")
    }
}

fun main(args: Array<String>) {
//    val n = readln().toInt()
//    val lines: MutableList<Pair<Int, Int>> = mutableListOf()
//    var minX = Int.MAX_VALUE
//    var maxX = Int.MIN_VALUE
//    for (i in 1..n) {
//        val line = readln().split(' ').map { it.toInt() }
//        lines.add(Pair(line[0], line[1]))
//        minX = min(minX, line[0])
//        maxX = max(maxX, line[1])
//    }
//    val listSize = lines.size
//    val sortedList = lines.sortedBy { it.first }
//    val points = mutableListOf<Int>()
////    println(sortedList)
////    println(sortedList::class.simpleName)
//    for (i in minX..maxX){
//        for(line in sortedList){
//            if (i >= line.first && i <= line.second){
//                points.add(i)
//            }else{
//                break
//            }
//        }
//    }
    val data = readln().split(" ").map { it.toInt() }
    val n = data[0]
    val w = data[1]
    val parts = mutableListOf<Triple<Int, Int, Float>>()
    for (i in 1..n) {
        val line = readln().split(' ').map { it.toInt() }
        val ci = line[0]
        val wi = line[1]
        parts.add(Triple(ci, wi, ci.toFloat() / wi))
    }
    val sortedParts = parts.sortedByDescending { it.third }
    var left = w
    var resultSum = 0.0f
    for (part in sortedParts) {
        if (left == 0) break
        if (part.second <= left) {
            resultSum += part.first
            left -= part.second
        }else{
            resultSum += left * part.third
        }
    }
    println(resultSum)

}