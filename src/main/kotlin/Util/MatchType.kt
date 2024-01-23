package Util

import Constant.LottoConstantPrize.FIVE_MATCH_PRICE
import Constant.LottoConstantPrize.FIVE_MATCH_WITH_BONUS_PRICE
import Constant.LottoConstantPrize.FOUR_MATCH_PRICE
import Constant.LottoConstantPrize.SIX_MATCH_PRICE
import Constant.LottoConstantPrize.THREE_MATCH_PRICE

enum class MatchType(val prize: Int) {

    THREE_MATCH(THREE_MATCH_PRICE),
    FOUR_MATCH(FOUR_MATCH_PRICE),
    FIVE_MATCH(FIVE_MATCH_PRICE),
    FIVE_MATCH_WITH_BONUS(FIVE_MATCH_WITH_BONUS_PRICE),
    SIX_MATCH(SIX_MATCH_PRICE)

}