package views

class MainScreen {

    fun menu(): Int {
        var option: Int
        var input: String?

        println("Welcome to Grattan Swimming Pool MAIN MENU\n")
        println("Press ok to continue")

        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            100
        return option
    }

    }
