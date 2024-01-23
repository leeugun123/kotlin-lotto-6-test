class Lotto (private val numbers: List<Int>) {

    init {
        require(numbers.size == 6)
        LottoData.lottoNum.add(numbers)
    }

    //추가 메소드 구현

    fun formatString() = numbers.sorted().joinToString{ it.toString() }


}