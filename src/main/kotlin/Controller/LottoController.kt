package Controller
import Model.LottoData.bonusNum
import Model.LottoData.lottoNum
import Model.LottoData.lottoNumFormats
import Model.LottoData.profitRatio
import Model.LottoData.purchaseNum
import Model.LottoData.stats
import Model.Lotto
import Model.LottoData.winningNumbers
import Util.MatchType
import camp.nextstep.edu.missionutils.Randoms


object LottoController {


    private const val LOTTO_RANGE_START = 1
    private const val LOTTO_RANGE_END = 45
    private const val LOTTO_DRAW_NUM = 6

    private const val THREE_MATCH = 3
    private const val FOUR_MATCH = 4
    private const val FIVE_MATCH = 5
    private const val SIX_MATCH = 6


    fun calculateInputMoney(inputMoney : Int){
        purchaseNum = inputMoney / 1000
    }

    fun lottoDraw() {
        repeat(purchaseNum) {
            val lottoNumList = Randoms.pickUniqueNumbersInRange(LOTTO_RANGE_START, LOTTO_RANGE_END, LOTTO_DRAW_NUM)
            lottoNumFormats.add(listOf(Lotto(lottoNumList).formatString()))
        }
    }


    fun analyzeLotto() {
        calculateStats(lottoNum, winningNumbers, bonusNum)
        profitRatio = calculateEarnings()
    }

    private fun calculateStats(purchasedTickets: List<List<Int>>, winningNumbers : List<Int>, bonusNumber : Int) {

        purchasedTickets.forEach { ticket ->

            val matchCount = ticket.intersect(winningNumbers.toSet()).size

            when (matchCount) {

                THREE_MATCH -> increaseStat(MatchType.THREE_MATCH)

                FOUR_MATCH -> increaseStat(MatchType.FOUR_MATCH)

                FIVE_MATCH -> {

                    if (ticket.contains(bonusNumber))
                        increaseStat(MatchType.FIVE_MATCH_WITH_BONUS)
                    else
                        increaseStat(MatchType.FIVE_MATCH)

                }

                SIX_MATCH -> increaseStat(MatchType.SIX_MATCH)

            }
        }
    }

    private fun calculateEarnings(): String {

        val totalEarnings : Int = stats.map { it.key.prize * it.value }.sum()
        val totalInvestment : Int = purchaseNum * 1000

        return getReturnRate(totalEarnings,totalInvestment)
    }

    private fun increaseStat(matchType: MatchType) {
        stats[matchType] = stats.getValue(matchType) + 1
    }



    private fun getReturnRate(totalEarning : Int , purchaseAmount: Int): String {
        val returnRate = (totalEarning.toDouble() / purchaseAmount) * 100
        return String.format("%.1f", returnRate)
    }


}

