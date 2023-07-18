fun main(){
    val param = Field.NAME
    var student = searchByParameter(param)
    while (student == null) {
        println("Wrong ${param}.Please enter a new $param and try Again")
        student = searchByParameter(param)
    }
    println(student)
}
enum class Field{
    NAME,
    ID
}
fun searchByParameter(searchField: Field): Student? {
    println("Please, Enter the $searchField of the student")
    val requiredSearchParameter :String = readln()
    return when (searchField) {
        Field.ID -> {
            val id: Int = requiredSearchParameter.toInt()
            getStudentsById(id)
        }
        Field.NAME -> {
            val name: String = requiredSearchParameter
            searchStudentsByName(name)
        }
    }

}
data class Student(
    val id :Int,
    val name :String,
    val grade :Double
)

val students = listOf(
    Student(223,"John", 140.0),
    Student(548,"Mark", 120.0),
    Student(342, "Natali", 150.0),
    Student(781,  "Sara", 130.0)
)
fun getStudentsById(id: Int): Student? {
    return students.find { it.id == id }
}
fun searchStudentsByName(name: String) :Student? {
    return students.find{it.name.lowercase() == name.lowercase()}
}