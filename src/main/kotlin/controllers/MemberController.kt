package controllers

import models.MemberMemStore
import models.MemberModel
import mu.KotlinLogging
import views.MemberViews

/******************************
*@author Ibrahim Bandaogo
*
********************************/

class MemberController {
    private val members = MemberMemStore()
    private val memberView = MemberViews()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info {"Launching Grattan Swimming Pool APP Console"}
        println(" Swimming Pool App Version 3.0")
    }

    // Function called to start the program
    fun start(){
        var input : Int

        do {
            input = menu()
            when(input){
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                100 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        }while (input!=-1)
        logger.info("Shutting Swimming Pool Console App")
    }

    //Function to displays menu
    private fun menu() : Int {
        return memberView.menu()
    }

    // Function to add member
    private fun add(){
        val aMember = MemberModel()

        if(memberView.addMemberData(aMember)) {
            members.create(aMember)
            logger.info("Member Added Successfully")
        }
        else
            logger.info("Members Not Added, Please check all fields and try again")
    }
    private fun list(){
        memberView.listMembers(members)
    }

    private fun update(){
        memberView.listMembers(members)
        val searchId = memberView.getId()
        val aMember = search(searchId)

        if(aMember != null){
            if(memberView.updateMemberData(aMember)){
                members.update(aMember)
                memberView.showMember(aMember)
                logger.info("Member Updated : [$aMember]")
            }else
                logger.info("Member Not Added")
        }else
            println("Member Not Added")
    }

    private fun delete(){
        memberView.listMembers(members)
        val searchId = memberView.getId()
        val memberDelete = search(searchId)

        if(memberDelete != null){
            members.delete(memberDelete)
            println("Member Deleted....")
            memberView.listMembers(members)
        }
        else
            println("Member Not Deleted....")
    }
// Member Search Function
    private fun search(){
        val aMember = search(memberView.getId())!!
        memberView.showMember(aMember)
    }
// ID Function
    private fun search(id: Int) : MemberModel?{
        return members.findOne(id)
    }

// Dummy data
    private fun dummyData(){
    members.create(MemberModel(1,"John Smith","Waterford","Student membership","08111111","Male"))

}
}

