package models

//Data class
data class MemberModel(
    var id: Int = 0,
    var fullName: String= "",
    var gender: String = "",
    var address: String = "",
    var memberEmail: String = "",
    var membershipTypes: String = ""
    //var emergencyContact: String = ""
    )