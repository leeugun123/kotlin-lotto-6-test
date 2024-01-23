object LottoData {

    var inputMoney = ""

    var purchaseNum = 0

    var lottoNumFormats = mutableListOf<List<String>>() //랜덤 으로 뽑은 로또 넘버 (String)

    var lottoNum = mutableListOf<List<Int>>()  //랜덤 으로 뽑은 로또 넘버

    var lottoResult = mutableListOf<Int>()

    var bonusNum = 0

    var stats = mutableMapOf<MatchType, Int>().withDefault { 0 }

    var profitRatio = 0.0


}