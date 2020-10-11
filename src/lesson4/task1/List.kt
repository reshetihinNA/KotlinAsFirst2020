@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i in v) abs += sqr(i)
    return sqrt(abs)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) list.sum()
    else list.sum() / list.size

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val middle = mean(list)
    if (list.isEmpty()) return listOf<Double>().toMutableList()
    for (i in list.indices) list[i] -= middle
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) c += a[i] * b[i]
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var f = 0
    var c = 1
    for (i in p.indices) {
        f += p[i] * c
        c *= x
    }
    return f
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    val copy = list.toList()
    if (list.isEmpty()) return listOf<Int>().toMutableList()
    for (i in list.indices) {
        list[i] += copy.subList(0, i).sum()
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    val list = listOf<Int>().toMutableList()
    while (!isPrime(number)) {
        for (i in 1..number) {
            if (isPrime(i) && number % i == 0) {
                number /= i
                list.add(i)
                break
            }
        }
    }
    list.add(number)
    list.sort()
    return list.toList()
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val f = factorize(n)
    var s = ""
    for (i in f.indices) {
        if (i != 0) s += "*"
        val fi = f[i]
        s += "$fi"
    }
    return s
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val list = listOf<Int>().toMutableList()
    while (number / base != 0) {
        list.add(0, number % base)
        number /= base
    }
    list.add(0, number)
    return list
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val convert = convert(n, base).toMutableList()
    val list =
        listOf(
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x",
            "y", "z"
        )
    var s = ""
    for (i in convert) {
        s += if (i > 9) list[i - 10]
        else "$i"
    }
    return s
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0
    var digit = 1
    for (i in digits.size - 1 downTo 0) {
        number += digits[i] * digit
        digit *= base
    }
    return number
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var number = 0
    var digit = 1
    val list =
        listOf(
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
        )
    for (i in str.length - 1 downTo 0) {
        number += list.indexOf(str[i]) * digit
        digit *= base
    }
    return number
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val list =
        listOf(
            "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX",
            "X", "XX", "XXX", "XL", "L",
            "LX", "LXX", "LXXX", "XC"
        )
    val listHundreds =
        listOf(
            "C", "CC", "CCC", "CD", "D",
            "DC", "DCC", "DCCC", "CM"
        )
    val listThousands =
        listOf(
            "M", "MM", "MMM"
        )
    val numberDigits = listOf<Int>().toMutableList()
    var number = ""
    numberDigits.add(0, n % 10)
    numberDigits.add(1, (n / 10) % 10)
    numberDigits.add(2, (n / 100) % 10)
    numberDigits.add(3, n / 1000)
    for (i in numberDigits.size - 1 downTo 0) {
        val c = numberDigits[i]
        if (c != 0) {
            if (i == 3) number += listThousands[c - 1]
            if (i == 2) number += listHundreds[c - 1]
            if (i == 1) number += list[c + 8]
            if (i == 0) number += list[c - 1]
        }
    }
    return number
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val list =
        listOf(
            "один", "два", "три", "четыре", "пять",
            "шесть", "семь", "восемь", "девять", "десять",
            "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать",
            "семнадцать", "восемнадцать", "девятнадцать",
            "дцать", "сорок", "десят", "девяносто"
        )
    val listHundreds =
        listOf(
            "сто", "двести",
            "ста", "сот",
        )
    val listThousands =
        listOf(
            "одна", "две", "тысяча", "тысячи", "тысяч"
        )
    val numberDigits = listOf<Int>().toMutableList()
    var number = ""
    numberDigits.add(0, n % 100)
    numberDigits.add(1, (n / 100) % 10)
    numberDigits.add(2, (n / 1000) % 100)
    numberDigits.add(3, n / 100000)
    for (i in numberDigits.size - 1 downTo 0) {
        if (i == 0 || i == 2) {
            val dec = numberDigits[i]
            if (dec != 0) {
                if (dec < 20) {
                    number += if (i == 2 && dec in 1..2) listThousands[dec - 1] + " "
                    else list[dec - 1] + " "
                } else {
                    if (dec / 10 in 2..3) number += list[(dec / 10) - 1] + list[19] + " "
                    if (dec / 10 == 4) number += list[20] + " "
                    if (dec / 10 in 5..8) number += list[(dec / 10) - 1] + list[21] + " "
                    if (dec / 10 == 9) number += list[22] + " "
                    if (dec % 10 != 0) {
                        number += if (i == 2 && dec % 10 in 1..2) listThousands[(dec % 10) - 1] + " "
                        else list[(dec % 10) - 1] + " "
                    }
                }
            }
            if (i == 2) {
                if (dec != 0 || numberDigits[3] != 0) {
                    number += when {
                        dec % 100 in 5..20 || dec % 10 >= 5 || dec % 10 == 0 -> listThousands[4] + " "
                        dec % 10 == 1 -> listThousands[2] + " "
                        else -> listThousands[3] + " "
                    }
                }
            }
        }
        if ((i == 1 || i == 3) && numberDigits[i] != 0) {
            val hundreds = numberDigits[i]
            if (hundreds == 1) number += listHundreds[0] + " "
            if (hundreds == 2) number += listHundreds[1] + " "
            if (hundreds in 3..4) number += list[hundreds - 1] + listHundreds[2] + " "
            if (hundreds in 5..9) number += list[hundreds - 1] + listHundreds[3] + " "
        }
    }
    number = number.substring(0, number.length - 1)
    return number
}