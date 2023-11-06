class CheckData {

    val INPUT_AGAIN = "다시 입력하세요."
    val WRONG_FORMAT = "잘못된 형식입니다. $INPUT_AGAIN"
    val WRONG_RANGE = "1~45까지의 숫자 범위 안에서 허용됩니다. $INPUT_AGAIN"

    fun checkInputMoney(inputMoney: String) {

        require(checkPrice(inputMoney)) { "1000원 밑으로는 구매할 수 없습니다." }
        require(checkDigitNum(inputMoney)) { "문자열에 숫자 이외의 문자가 포함되어 있습니다." }
        require(checkDivide(inputMoney)) { "1000원 단위로 떨어지지 않습니다." }

    }

    fun checkLottoNum(lottoNum : String){

        val numbers = lottoNum.split(",")

        require(checkDigitList(numbers)) { WRONG_FORMAT }
        require(checkLottoCount(numbers)) { "6개의 숫자를 ,를 기준으로 입력해주세요." }
        require(checkLottoRange(numbers)) { WRONG_RANGE }
        require(checkDuplicateNumbers(numbers)) { "중복된 숫자가 있습니다. $INPUT_AGAIN" }

    }

    fun checkBonusNum(bonusNum : String){

        require(checkDigitNum(bonusNum)) {WRONG_FORMAT}
        require(checkRange(bonusNum)){WRONG_RANGE}

    }

    private fun checkDuplicateNumbers(list : List<String>) = list.size == list.toSet().size

    private fun checkLottoCount(numbers : List<String>) = numbers.size == 6

    private fun checkLottoRange(numbers : List<String>) = numbers.all { checkRange(it) }

    private fun checkDigitList(input : List<String>) =  input.all { it.isNumeric() }

    private fun checkDigitNum(input : String) = input.toIntOrNull() != null

    private fun checkDivide(inputMoney : String) =  Integer.parseInt(inputMoney) % 1000 == 0

    private fun checkPrice(inputMoney : String) = Integer.parseInt(inputMoney) >= 1000

    private fun checkRange(lotto : String) = Integer.parseInt(lotto) in 1..45

    private fun String.isNumeric(): Boolean {
        return this.matches("-?\\d+(\\.\\d+)?".toRegex())
    }


}