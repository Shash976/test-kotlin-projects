fun main(args: Array<String>) {
    val p1 = Player("Nicola", "Tesla")
    val scoring :Scores = Scores()
    var lvlScore = 1
    p1.totalScore += lvlScore
    p1.personalBestScore = scoring.getBestScore(p1.personalBestScore, lvlScore)
    lvlScore = 20
    p1.totalScore += lvlScore
    p1.personalBestScore = scoring.getBestScore(p1.personalBestScore, lvlScore)
    lvlScore = 35
    p1.totalScore += lvlScore
    p1.personalBestScore = scoring.getBestScore(p1.personalBestScore, lvlScore)
    lvlScore = 25
    p1.totalScore += lvlScore
    p1.personalBestScore = scoring.getBestScore(p1.personalBestScore, lvlScore)
    println("Winner ${p1.fullName()} with a combined score of ${p1.totalScore} and a personal best score of ${p1.personalBestScore}")
}

class Player(
    private var name :String,
    private var surname :String
) {
    var totalScore = 0
    var personalBestScore = 0
    fun fullName(): String {
        val player: Player = this
        return "${player.name} ${player.surname}"
    }
}

class Scores() {
    fun getBestScore(best :Int, current :Int) :Int {
        if (best < current){
            return current
        }
        return best
    }
}