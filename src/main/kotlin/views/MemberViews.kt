package views

import models.MemberMemStore
import models.MemberModel
import java.util.regex.Pattern


class MemberViews {
    // Menu function
    fun menu(): Int {
        var option: Int
        var input: String?

        println("Grattan Swimming Pool MAIN MENU")
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

    // Function that displays members list
    fun listMembers(members: MemberMemStore) {
        println("Members List")
        println()
        members.logAll()
        println()
    }

    // Adding member function
    fun addMemberData(member: MemberModel): Boolean {
        println()
        print("Enter full name : ")
        member.fullName = readLine()!!
        print("Your Gender (Male/Female): ")
        member.gender = readLine()!!
        print("Enter your Address: ")
        member.address = readLine()!!
        print("Membership Types (Single/Couple/Student): ")
        member.membershipTypes = readLine()!!
        print("Enter Your Email: ")
        member.memberEmail = readLine()!!
        print("Enter Emergency Contact Number: ")
        member.emergencyContact = readLine()!!
        return member.fullName.isNotEmpty() &&
                member.address.isNotEmpty() &&
                member.membershipTypes.isNotEmpty() &&
                member.memberEmail.isEmailValid() &&
                member.emergencyContact.isNotEmpty()
    }
    // Function to display member detail
    fun showMember(member: MemberModel) {
        println("Member Details [$member]")
    }

    // Function that enables to update member details
    fun updateMemberData(member: MemberModel): Boolean {
        var tempFullName: String?
        var tempGender: String?
        var tempAddress: String?
        var tempMembershipTypes: String?
        var tempMemberEmail: String?
        var tempEmergencyContact: String?

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
        println("Enter a new member Emergency Contact for [ " + member.emergencyContact + " ] : ")
        tempEmergencyContact = readLine()!!
        if (!tempFullName.isNullOrEmpty() && !tempMemberEmail.isNullOrEmpty() && !tempEmergencyContact.isNullOrEmpty()) {
            member.fullName = tempFullName
            member.gender = tempGender
            member.address = tempAddress
            member.membershipTypes = tempMembershipTypes
            member.memberEmail = tempMemberEmail
            member.emergencyContact = tempEmergencyContact

            return true
        }
        return false
    }

    // Function used for searching, deleting using get an id
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

    // function used to validate the email pattern. if not valid member can't be added
    fun String.isEmailValid(): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }
}



