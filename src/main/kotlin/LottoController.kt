import CheckData.checkBonusNum
import CheckData.checkInputMoney
import CheckData.checkLottoNum
import LottoCalculating.calculateEarnings
import LottoCalculating.calculateStats
import LottoResults.bonusNum
import LottoResults.lottoNums
import LottoResults.lottoResult
import LottoResults.profitRatio
import LottoResults.stats
import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

object LottoController {

    fun lottoPurchase(): Int {
        while (true) {
            try {
                println("구입금액을 입력해주세요.")
                val inputMoney = Console.readLine()
                checkInputMoney(inputMoney)

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
        lottoResult = inputLottoNum()
        bonusNum = inputBonusNum()
    }

    fun analyzeLotto(){
        calculateStats(lottoNums, lottoResult, bonusNum)
        profitRatio = calculateEarnings()
    }

    fun showResult(){

        println("당첨 통계")
        println("---")

        showLottoStatic()
        println("총 수익률은 $profitRatio%입니다.")

    }

    private fun showLottoStatic(){

        for (matchType in MatchType.entries) {

            when(matchType.name){

                "THREE_MATCH" -> println("3개 일치 (${matchType.prize}원) - ${stats.getValue(matchType)}개")
                "FOUR_MATCH" -> println("4개 일치 (${matchType.prize}원) - ${stats.getValue(matchType)}개")
                "FIVE_MATCH" -> println("5개 일치 (${matchType.prize}원) - ${stats.getValue(matchType)}개")
                "FIVE_MATCH_WITH_BONUS" -> println("5개 일치, 보너스 볼 일치 (${matchType.prize}원) - ${stats.getValue(matchType)}개")
                "SIX_MATCH" -> println("6개 일치 (${matchType.prize}원) - ${stats.getValue(matchType)}개")
            }

        }

    }



    private fun inputLottoNum(): MutableList<Int> {
        while (true) {
            try {
                println("당첨 번호를 입력해 주세요.")
                val inputNum = Console.readLine()
                checkLottoNum(inputNum)
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
                checkBonusNum(bonusNum)
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
