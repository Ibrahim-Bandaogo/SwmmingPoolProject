package models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }
var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class MemberMemStore: MemberStore {
    var maxMembers = 2 // limit members numbers to add
    private val members = ArrayList<MemberModel>()

    override fun findAll() : List<MemberModel>{
        return members
    }

    // Override findOne abstract function
    override fun findOne(id: Int): MemberModel? {
        return members.find { m -> m.id == id }
    }

    // Override create abstract function
    override fun create(member: MemberModel) {
        // Added member should not be over Max
        if(members.size < maxMembers){
            member.id = getId()
            members.add(member)
            logAll()
        }
    }

    // Override update abstract function
    override fun update(member: MemberModel) {
        val foundMember = findOne(member.id)
        if(foundMember != null){
            foundMember.fullName = member.fullName
            foundMember.gender = member.gender
            foundMember.address = member.address
            foundMember.membershipTypes = member.membershipTypes
            foundMember.memberEmail = member.memberEmail
            //foundMember.emergencyContact = member.emergencyContact
        }
    }

    // overrides delete abstract function
    override fun delete(member: MemberModel) {
        val foundMember = findOne(member.id)
        if(foundMember != null){
            members.remove(foundMember)
        }
    }

    // function to display all the members
    internal fun logAll(){
        members.forEach { logger.info ("$it")}
    }
}
