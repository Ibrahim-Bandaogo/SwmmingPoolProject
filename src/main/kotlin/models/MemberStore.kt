package models

interface MemberStore {
    fun findAll(): List<MemberModel>
    fun finOne(id: Int) : MemberModel?
    fun create(member: MemberModel)
    fun update(member: MemberModel)
    fun delete(member: MemberModel)
    fun findOne(id: Int): MemberModel?
}