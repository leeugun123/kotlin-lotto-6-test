import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

class LottoController {

    private val checkData = CheckData()

    fun lottoPurchase(): Int {
        while (true) {
            try {
                println("구입금액을 입력해주세요.")
                val inputMoney = Console.readLine()
                checkData.checkInputMoney(inputMoney)

                val lottoCount = inputMoney.toInt() / 1000
                println("$lottoCount 개를 구매했습니다.")

                return lottoCount
            } catch (e: IllegalArgumentException) {
                println("예외 발생: ${e.message} 다시 입력해주세요.")
            }
        }
    }

    fun lottoDraw(purchaseNum: Int) {
        repeat(purchaseNum) {
            val lottoNum = Randoms.pickUniqueNumbersInRange(1, 45, 6)
            Lotto(lottoNum).printNumbers()
        }
    }

    fun inputNum() {
        LottoResults.lottoResult = inputLottoNum()
        LottoResults.lottoResult.add(inputBonusNum())
    }

    private fun inputLottoNum(): MutableList<Int> {
        while (true) {
            try {
                println("당첨 번호를 입력해 주세요.")
                val inputNum = Console.readLine()
                checkData.checkLottoNum(inputNum)
                return addStringToList(inputNum)
            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }
        }
    }

    private fun inputBonusNum(): Int {
        while (true) {
            try {
                println("보너스 번호를 입력해 주세요.")
                val bonusNum = Console.readLine()
                checkData.checkBonusNum(bonusNum)
                return bonusNum.toInt()
            } catch (e: IllegalArgumentException) {
                println("${e.message}")
            }
        }
    }

    private fun addStringToList(input: String): MutableList<Int> {
        return input.split(",").map { it.trim().toInt() }.toMutableList()
    }


}
