class CheckData {

    val INPUT_AGAIN = "다시 입력하세요."
    val WRONG_FORMAT = "잘못된 형식입니다. $INPUT_AGAIN"
    val WRONG_RANGE = "1~45까지의 숫자 범위 안에서 허용됩니다. $INPUT_AGAIN"

    fun checkInputMoney(inputMoney : String){

        if(!checkPrice(inputMoney))
            throw IllegalArgumentException("1000원 밑으로는 구매 할 수 없습니다.")

        if(!checkDigitNum(inputMoney))
            throw IllegalArgumentException("문자열에 숫자 이외의 문자가 포함되어 있습니다.")

        if(!checkDivide(inputMoney))
            throw IllegalArgumentException("1000원 단위로 떨어지지 않습니다.")


    }

    fun checkLottoNum(lottoNum : String){

        val numbers = lottoNum.split(",")

        if (!checkDigitList(numbers))
            throw IllegalArgumentException(WRONG_FORMAT)


        if (!checkLottoCount(numbers))
            throw IllegalArgumentException("6개의 숫자를 ,를 기준으로 입력해주세요.")


        if(!checkLottoRange(numbers))
            throw IllegalArgumentException(WRONG_RANGE)

    }

    fun checkBonusNum(bonusNum : String){

        if(!checkDigitNum(bonusNum))
            throw IllegalArgumentException(WRONG_FORMAT)

        if(!checkRange(bonusNum))
            throw IllegalArgumentException(WRONG_RANGE)

    }

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