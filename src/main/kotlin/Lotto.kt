import java.util.Collections


class Lotto (private val numbers: List<Int>) {

    init {
        require(numbers.size == 6)
        LottoData.lottoNums.add(numbers)
    }

    //추가 메소드 구현

    fun printNumbers(){

        val formattedString = numbers.sorted().joinToString(prefix = "[", postfix = "]") { it.toString() }
        println(formattedString)

    }


}