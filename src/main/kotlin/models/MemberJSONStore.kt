package models
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import helpers.*
import java.lang.reflect.Type
import java.util.ArrayList
import kotlin.random.Random
import helpers.write
import helpers.read
import helpers.exists




    private val logger = KotlinLogging.logger { }

    const val JSON_FILE = "members.json"
    val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().create()
    val listType: Type? = object : TypeToken<ArrayList<MemberModel>>() {}.type

    fun generateRandomId(): Int {
    return Random.nextInt()
}


abstract class MemberJSONStore : MemberStore {
    private var members = mutableListOf<MemberModel>()

    init {
        if(exists(JSON_FILE)){
            deserialize()
        }
    }
    // overrides the findAll abstract function and implement the class function
    override fun findAll(): MutableList<MemberModel> {
        return members
    }

    // overrides the findOne abstract function and implement the class function
    override fun findOne(id: Int): MemberModel? {
        return members.find { m -> m.id == id }
    }

    // overrides the create abstract function and implement the class function
    override fun create(member: MemberModel) {
        member.id = generateRandomId()
        members.add(member)
        serialize()
    }

    // overrides the update abstract function and implement the class function
    override fun update(member: MemberModel) {
        val foundMember = findOne(member.id)
        if(foundMember != null){
            foundMember.fullName = member.fullName
            foundMember.address = member.address
            foundMember.membershipTypes = member.membershipTypes
            foundMember.memberEmail = member.memberEmail
            foundMember.gender = member.gender
        }
        serialize()
    }

    // overrides the delete abstract function and implement the class function
    override fun delete(member: MemberModel) {
        val foundMember = findOne(member.id)
        if(foundMember != null){
            members.remove(foundMember)
        }
        serialize()
    }

    // function to display all the members
    internal fun logAll(){
        members.forEach { logger.info("$it")}
    }

    // function to convert the member type to json format and persist
    private fun serialize(){
        val jsonString = gsonBuilder.toJson(members, listType)
        write(JSON_FILE, jsonString)
    }

    // function to convert from json format to member type the persisted datta
    private fun deserialize(){
        val jsonString = read(JSON_FILE)
        members = Gson().fromJson(jsonString, listType)
    }

}
