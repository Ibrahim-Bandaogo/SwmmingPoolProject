package views

import models.MemberJSONStore
import models.MemberMemStore
import models.MemberModel
import java.util.regex.Pattern


class MemberViews {

    fun menu(): Int {
        var option: Int
        var input: String?

        println("Park Swimming Pool MAIN MENU")
        println(" 1. Add Member")
        println(" 2. Update Member Details")
        println(" 3. List All Members")
        println(" 4. Search Member")
        println(" 5. Delete Member")
        println(" -99. Dummy Data")
        println(" 100. Exit")
        println()
        print("Enter an Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            100
        return option
    }

    fun listMembers(members: MemberMemStore) {
        println("Members List")
        println()
        members.logAll()
        println()
    }

    fun listMembers(members: MemberJSONStore) {
        println("Members List")
        println()
        members.logAll()
        println()
    }

    fun addMemberData(member: MemberModel): Boolean {
        println()
        print("Enter full name : ")
        member.fullName = readLine()!!
        print("Your Gender (Male/Female): ")
        member.gender = readLine()!!
        print("Enter your Address: ")
        member.address = readLine()!!
        print("Membership Types : ")
        member.membershipTypes = readLine()!!
        print("Enter Your Email: ")
        member.memberEmail = readLine()!!
        //print("Enter Emergency Contact Number: ")
        //member.EmergencyContact = readLine()!!
        return member.fullName.isNotEmpty() &&
                member.address.isNotEmpty() &&
                member.membershipTypes.isNotEmpty() &&
                member.memberEmail.isEmailValid()
                //member.EmergencyContact.isEmailValid()
    }

    fun showMember(member: MemberModel) {
        println("Member Details [$member]")
    }

    fun updateMemberData(member: MemberModel): Boolean {
        var tempFullName: String?
        var tempGender: String?
        var tempAddress: String?
        var tempMembershipTypes: String?
        var tempMemberEmail: String?
        //var tempEmergencyContact: String?


        print("Enter a new full name for [ " + member.fullName + " ] : ")
        tempFullName = readLine()!!
        print("Enter a new Member Gender for [ " + member.gender + " ] : ")
        tempGender = readLine()!!
        print("Enter a new address for [ " + member.address + " ] : ")
        tempAddress = readLine()!!
        print("Enter a new Membership Types [ " + member.membershipTypes + " ] : ")
        tempMembershipTypes = readLine()!!
        print("Enter a new member email for [ " + member.memberEmail + " ] : ")
        tempMemberEmail = readLine()!!

        if (!tempFullName.isNullOrEmpty() && !tempMemberEmail.isNullOrEmpty()) {
            member.fullName = tempFullName
            member.gender = tempGender
            member.address = tempAddress
            member.membershipTypes = tempMembershipTypes
            member.memberEmail = tempMemberEmail

            return true
        }
        return false
    }

    fun getId(): Int {
        var strId: String?     // string to hold user input
        var searchId: Int     // Long to hold converted id
        print("Enter Id to search/update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toInt()
        else
            -9
        return searchId
    }

    // function used to validate the email pattern. if not valid member wont be added
    fun String.isEmailValid(): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}



