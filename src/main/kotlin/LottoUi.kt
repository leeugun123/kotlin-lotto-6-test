import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

class LottoUi {

    //로또 구입 시작
    fun lottoExc(){

        val purchaseNum = lottoPurchase()
        println("$purchaseNum"+"개를 구매했습니다.")


        lottoDraw(purchaseNum)
        //로또 뽑기

        inputLottoNum()



    }

    private fun lottoPurchase(): Int {
        while (true) {
            try {
                println("구입금액을 입력해주세요.")
                val inputMoney = Console.readLine()
                CheckData().checkInputMoney(inputMoney)
                return Integer.parseInt(inputMoney) / 1000
            } catch (e: IllegalArgumentException) {
                println("예외 발생: ${e.message} 다시 입력해주세요.")
            }
        }
    }

    private fun lottoDraw(purchaseNum : Int) {

        repeat(purchaseNum){

            val lottoNum = Randoms.pickUniqueNumbersInRange(1,45,6)

            Lotto(lottoNum).printNumbers()
            //당첨된 로또 번호 출력

        }//구매한 로또 개수 만큼 반복

    }

    private fun inputLottoNum(){

        LottoResults.lottoResult = checkInputLottoNum()

        LottoResults.lottoResult.add(checkInputBonusNum())


    }

    private fun checkInputLottoNum() : MutableList<Int>{

        while (true) {

            try {
                println("당첨 번호를 입력해 주세요.")
                val inputNum = Console.readLine()
                CheckData().checkLottoNum(inputNum)

                return addStringToList(inputNum)

            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }

        }

    }

    private fun checkInputBonusNum() : Int{

        while (true) {

            try {

                println("보너스 번호를 입력해 주세요.")
                val bonusNum = Console.readLine()
                CheckData().checkBonusNum(bonusNum)
                return bonusNum.toInt()

            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }

        }

    }

    private fun addStringToList(input: String): MutableList<Int> {
        val numbers = input.split(",").map { it.trim().toInt() }
        return numbers.toMutableList()
    }


}