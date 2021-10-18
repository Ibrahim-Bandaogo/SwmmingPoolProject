package models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {  }
var lastId = 500

internal fun getId(): Int {
    return lastId++
}

class MemberMemStore: MemberStore {
    private val members = ArrayList<MemberModel>()

    override fun findAll() : List<MemberModel>{
        return members
    }

    override fun finOne(id: Int): MemberModel? {
        return members.find { m -> m.id == id }
    }

    override fun findOne(id: Int) : MemberModel?{
        return members.find { m -> m.id == id }
    }

    override fun create(member: MemberModel) {
        var max = 10
        // limit members numbers to add
        if(members.size < max){
            member.id = getId()
            members.add(member)
            logAll()
        }
    }

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

    // overrides the delete abstract function and implement the class function
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
