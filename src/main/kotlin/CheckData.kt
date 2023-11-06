class CheckData {

    fun checkInputMoney(inputMoney : String){

        if(!checkDigit(inputMoney))
            throw IllegalArgumentException("문자열에 숫자 이외의 문자가 포함되어 있습니다.")

        if(!checkDivide(inputMoney))
            throw IllegalArgumentException("1000원 단위로 떨어지지 않습니다.")


    }

    private fun checkDigit(inputMoney : String) = inputMoney.all { it.isDigit() }

    private fun checkDivide(inputMoney : String) =  Integer.parseInt(inputMoney) % 1000 == 0




}