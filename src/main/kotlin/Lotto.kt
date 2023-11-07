import LottoResults.lottoNums
import java.util.Collections


class Lotto (private val numbers: List<Int>) {

    init {
        require(numbers.size == 6)
        Collections.sort(numbers)
        lottoNums.add(numbers)
    }

    //추가 메소드 구현

    fun printNumbers(){

        val formattedString = numbers.joinToString(prefix = "[", postfix = "]") { it.toString() }
        println(formattedString)

    }


}