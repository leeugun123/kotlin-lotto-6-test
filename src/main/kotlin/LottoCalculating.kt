import LottoData.stats

object LottoCalculating {


    fun calculateStats(purchasedTickets: List<List<Int>>, winningNumbers: List<Int>, bonusNumber: Int) {

        for (ticket in purchasedTickets) {

            val matchCount = ticket.intersect(winningNumbers).size

            when (matchCount) {

                3 -> stats[MatchType.THREE_MATCH] = stats.getValue(MatchType.THREE_MATCH) + 1
                4 -> stats[MatchType.FOUR_MATCH] = stats.getValue(MatchType.FOUR_MATCH) + 1
                5 -> if (ticket.contains(bonusNumber)) {
                    stats[MatchType.FIVE_MATCH_WITH_BONUS] = stats.getValue(MatchType.FIVE_MATCH_WITH_BONUS) + 1
                } else {
                    stats[MatchType.FIVE_MATCH] = stats.getValue(MatchType.FIVE_MATCH) + 1
                }
                6 -> stats[MatchType.SIX_MATCH] = stats.getValue(MatchType.SIX_MATCH) + 1

            }

        }

    }

    fun calculateEarnings(): Double {

        val totalEarnings = stats.map { it.key.prize * it.value }.sum()
        val totalInvestment = LottoData.purchaseNum * 1000

        return (totalEarnings.toDouble() / totalInvestment * 100).roundTo2DecimalPlaces()

    }

    private fun Double.roundTo2DecimalPlaces() = "%,.2f".format(this).toDouble()


}