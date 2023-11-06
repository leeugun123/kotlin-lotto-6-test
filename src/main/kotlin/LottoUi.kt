import camp.nextstep.edu.missionutils.Console

class LottoUi {

    fun lottoExc(){

        while(true){

            try {

                println("구입금액을 입력해주세요.")
                val inputMoney = Console.readLine()
                CheckData().checkInputMoney(inputMoney)

                break
            }
            catch (e : IllegalArgumentException){

                println("예외 발생: ${e.message} 다시 입력해주세요.")

            }


        }











    }


}