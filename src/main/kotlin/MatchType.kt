import LottoConstantPrize.FIVE_MATCH_PRICE
import LottoConstantPrize.FIVE_MATCH_WITH_BONUS_PRICE
import LottoConstantPrize.FOUR_MATCH_PRICE
import LottoConstantPrize.SIX_MATCH_PRICE
import LottoConstantPrize.THREE_MATCH_PRICE

enum class MatchType(val prize: Int) {

    THREE_MATCH(THREE_MATCH_PRICE),
    FOUR_MATCH(FOUR_MATCH_PRICE),
    FIVE_MATCH(FIVE_MATCH_PRICE),
    FIVE_MATCH_WITH_BONUS(FIVE_MATCH_WITH_BONUS_PRICE),
    SIX_MATCH(SIX_MATCH_PRICE)

}