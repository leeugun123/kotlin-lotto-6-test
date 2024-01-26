package View

import ExceptionHandler.CheckData.checkBonusNum
import ExceptionHandler.CheckData.checkInputMoney
import ExceptionHandler.CheckData.checkLottoNum
import Controller.LottoController
import Model.LottoData
import Model.LottoData.bonusNum
import Model.LottoData.inputMoney
import Model.LottoData.lottoNumFormats
import Model.LottoData.profitRatio
import Model.LottoData.purchaseNum
import Model.LottoData.stats
import Util.MatchType
import camp.nextstep.edu.missionutils.Console
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class LottoUI {

    companion object{
        const val INPUT_PURCHASE_MONEY_GUIDE = "구입금액을 입력해주세요."
        const val PURCHASE_LOTTO_NUM_GUIDE = "개를 구매했습니다."
        const val EXCEPTION_OCCUR = "예외 발생:"
        const val INPUT_AGAIN = "다시 입력해주세요."
        const val INPUT_WINNING_NUMBER = "당첨 번호를 입력해 주세요."
        const val INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요."
        const val WINNING_STATUS = "당첨 통계"
        const val LINE = "---"
        const val TOTAL_GUIDE_START = "총 수익률은 "
        const val TOTAL_GUIDE_END ="%입니다."

        const val THREE_BINGO = "3개 일치"
        const val FOUR_BINGO = "4개 일치"
        const val FIVE_BINGO = "5개 일치"
        const val FIVE_BINGO_PLUS_BONUS = "5개 일치, 보너스 볼 일치"
        const val SIX_BINGO = "6개 일치"
    }

    //로또 구입 시작
    fun lottoProcess(){

        lottoPurchase() //로또 구매
        LottoController.lottoDraw() //로또 뽑기
        lottoNumPrint() //로또 뽑은 로또 출력
        inputLottoAndBonus() //로또 번호 입력 받기
        LottoController.analyzeLotto() //로또 번호 분석
        showResult() //로또 당첨 결과 출력

    }

    private fun lottoNumPrint() {
        lottoNumFormats.forEach { lottoNumFormat ->
            println(lottoNumFormat)
        }
    }

    private fun lottoPurchase()  {

        while (true) {
            try {
                inputPurchaseMoney()
                calculateLottoNum()
                return
            } catch (e: IllegalArgumentException) { println(EXCEPTION_OCCUR + e.message + INPUT_AGAIN) }
        }

    }

    private fun inputPurchaseMoney() {
        println(INPUT_PURCHASE_MONEY_GUIDE)
        inputMoney = Console.readLine()
        checkInputMoney()
    }

    private fun calculateLottoNum() {
        LottoController.calculateInputMoney(inputMoney.toInt())
        println("$purchaseNum" + PURCHASE_LOTTO_NUM_GUIDE)
    }

    private fun inputLottoAndBonus() {
        LottoData.winningNumbers = inputLottoNum()
        bonusNum = inputBonusNum()
    }

    private fun inputLottoNum(): MutableList<Int> {
        while (true) {
            try {
                println(INPUT_WINNING_NUMBER)
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
                println(INPUT_BONUS_NUMBER )
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

    private fun showResult() {
        println(WINNING_STATUS)
        println(LINE)
        showLottoStats()
        println("$TOTAL_GUIDE_START$profitRatio$TOTAL_GUIDE_END")
    }

    private fun showLottoStats() {
        for (matchType in MatchType.entries) {
            val prizeText = convertToMoneyFormat(matchType.prize) + "원"
            val statCount = stats.getValue(matchType)
            val matchTypeText = when (matchType) {
                MatchType.THREE_MATCH -> THREE_BINGO
                MatchType.FOUR_MATCH -> FOUR_BINGO
                MatchType.FIVE_MATCH -> FIVE_BINGO
                MatchType.FIVE_MATCH_WITH_BONUS -> FIVE_BINGO_PLUS_BONUS
                MatchType.SIX_MATCH -> SIX_BINGO
            }
            println("$matchTypeText ($prizeText) - " + "$statCount" + "개")
        }
    }

    private fun convertToMoneyFormat(number: Int): String {
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.KOREA)
        (formatter as DecimalFormat).applyPattern("###,###")
        return formatter.format(number.toLong())
    }

}