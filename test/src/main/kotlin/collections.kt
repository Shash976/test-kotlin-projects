fun main(){
    for (comment in comments) {
        if (comment.userID in blockedUserIds){
            continue
        } else if (comment.userID in userIdToRelation) {
            println("Comment ${comment.message} from ${userIdToRelation[comment.userID]}")
        } else {
            println("Comment ${comment.message}")
        }
    }
}

data class Comment(val userID :Int, val message :String)
val comments = listOf(
    Comment(5241,"Nice Code"),
    Comment(6624,"Like It"),
    Comment(5224,"What's going on"),
    Comment(9001, "👍"),
    Comment(8818, "Nice Code"),
)
var blockedUserIds = setOf<Int>(5224, 9001)
var userIdToRelation = mapOf(5241 to "Friend", 6624 to "Work Colleague")