package Controller
import Model.LottoData.bonusNum
import Model.LottoData.lottoNum
import Model.LottoData.lottoNumFormats
import Model.LottoData.lottoResult
import Model.LottoData.profitRatio
import Model.LottoData.purchaseNum
import Model.LottoData.stats
import Model.Lotto
import Model.LottoData
import Util.MatchType
import camp.nextstep.edu.missionutils.Randoms


object LottoController {

    private const val LOTTO_RANGE_START = 1
    private const val LOTTO_RANGE_END = 45
    private const val LOTTO_DRAW_NUM = 6

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
        calculateStats(lottoNum, lottoResult, bonusNum)
        profitRatio = calculateEarnings()
    }

    private fun calculateStats(purchasedTickets: List<List<Int>>, winningNumbers: List<Int>, bonusNumber: Int) {

        purchasedTickets.forEach { ticket ->
            val matchCount = ticket.intersect(winningNumbers.toSet()).size

            when (matchCount) {

                3 -> increaseStat(MatchType.THREE_MATCH)

                4 -> increaseStat(MatchType.FOUR_MATCH)

                5 -> if (ticket.contains(bonusNumber))
                    increaseStat(MatchType.FIVE_MATCH_WITH_BONUS)
                else increaseStat(MatchType.FIVE_MATCH)

                6 -> increaseStat(MatchType.SIX_MATCH)

            }
        }
    }

    private fun calculateEarnings(): Double {

        val totalEarnings = stats.map { it.key.prize * it.value }.sum()
        val totalInvestment = LottoData.purchaseNum * 1000
        return (totalEarnings.toDouble() / totalInvestment * 100).roundTo2DecimalPlaces()

    }

    private fun increaseStat(matchType: MatchType) {
        stats[matchType] = stats.getValue(matchType) + 1
    }


    private fun Double.roundTo2DecimalPlaces() = "%,.2f".format(this).toDouble()




}

