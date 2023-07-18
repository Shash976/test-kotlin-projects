fun main() {
    val accountType = when(getChoice()){
        1 -> Accounts.DEBIT
        2 -> Accounts.CREDIT
        3 -> Accounts.CHECKING
        else -> Accounts.CREDIT
    }
    println("You have created a ${accountType.toString().lowercase().replaceFirstChar { it.uppercase() }} account \n")
    var accountBalance = 0
    var isSystemOpen = true

    while(isSystemOpen) {
        val action = when (getActionIndex()) {
            1 -> Actions.BALANCE
            2 -> Actions.WITHDRAW
            3 -> Actions.DEPOSIT
            4 -> Actions.QUIT
            else -> Actions.QUIT
        }
        when (action) {
            Actions.BALANCE -> println("The current balance is $accountBalance")
            Actions.DEPOSIT -> accountBalance = transfer(accountType, action, accountBalance)
            Actions.WITHDRAW -> accountBalance = transfer(accountType, action, accountBalance)
            Actions.QUIT -> {
                isSystemOpen = false
                println("System is closed")
            }
        }
    }
}

fun transfer(accountType: Accounts, method :Actions, accountBalance: Int) :Int{
    var money = accountBalance
    when(method){
        Actions.BALANCE -> {}
        Actions.WITHDRAW -> {
            print("How much money do you want to withdraw?")
            val amount = readln().toInt()
            println("Your account balance is ¤$accountBalance")
            money = withdraw(amount, accountBalance, accountType)
            println("You withdrew ¤${accountBalance-money} and your current balance is $money")
        }
        Actions.DEPOSIT -> {
            val beforeDeposit = money
            money = deposit(accountType, money)
            println("You deposited ${money-beforeDeposit}")
        }
        Actions.QUIT -> {}
    }
    return money
}

fun getActionIndex() :Int?{
    println("Which action would you like to perform? ")
    var i = 1
    for (action in Actions.values()) {
        println("\t $i. $action")
        i +=1
    }
    var actionIndex :Int?= 0
    while(actionIndex !in 1..Actions.values().size) {
        print("\t \t Which action would you like to perform? ")
        for (n in 1..Actions.values().size) {
            if (n == Actions.values().size) {
                print("or $n ")
            } else {
                print("$n,")
            }
        }
        actionIndex = readln().toIntOrNull()
    }
    return actionIndex
}

fun deposit(accountType: Accounts, currentBalance :Int =0)  :Int {
    var accountBalance = currentBalance
    if (accountType == Accounts.DEBIT || accountType == Accounts.CHECKING) {
        print("How much money would you like to deposit? ")
        val deposit = readln().toInt()
        accountBalance += deposit
        println("Your current balance is $accountBalance")
    }
    else if (accountType == Accounts.CREDIT) {
        if (accountBalance == 0) {
            println("Your debt is 0. No need to pay.")
        }
        else if (accountBalance < 0) {
            println("You have a debt of $accountBalance")
            print("How much would you like to pay? ")
            val amount = readln().toInt()
            if (amount > -accountBalance) {
                println("Deposit failed. You tried to pay off an amount greater than your credit balance.\nThe account balance is 0. \nThe surplus amount is ${amount + accountBalance}")
                accountBalance = 0
            }
            else if (amount < -accountBalance) {
                println("You have paid off $amount from you credit balance of ${-accountBalance}")
                println("Your balance is ${amount + accountBalance}")
                accountBalance += amount
            }
            else {
                println("Your debt is payed.")
                accountBalance += amount
            }
        }
    }
    return accountBalance
}
fun withdraw(amount :Int, accountBalance :Int, accountType:Accounts) :Int{
    var balance = accountBalance
    if (amount > balance) {
        if (accountType == Accounts.DEBIT) {
            println("Not possible, please try again. The withdrawal amount is greater than the account balance. This is not possible in a Debit account")
        }
        else {
            balance -= amount
            println("You have withdrawn ¤$amount from your $accountType account. Your current balance is ¤$balance")
        }
    }
    else if (amount == balance) {
        println("The amount you want to withdraw is equal to your current balance. Do you still want to withdraw?")
        print("Y/n")
        val ans = readln()
        if (ans[0].lowercase() == "y") {
            println("\t You have withdrawn $amount from your account. Your remaining balance is 0. Thank you.")
            balance -= amount
        }
        else if (ans[0].lowercase() == "n") {
            println("\t Very well.")
        }
    }
    else if (amount < balance) {
        println("You have withdrew ¤$amount from your account")
        println("Your remaining balance is ¤${balance-amount}")
        balance -= amount
    }
    return balance
}

enum class Actions{
    BALANCE,
    WITHDRAW,
    DEPOSIT,
    QUIT
}

enum class Accounts{
    DEBIT,
    CREDIT,
    CHECKING
}

fun getChoice(): Int? {
    var userChoice :Int?= 0
    while (userChoice !in  1..3){
        println("Welcome to your banking system \nWhat type of account would you like to create?")
        var i = 1
        for (account in Accounts.values()) {
            println("\t \t $i. ${account.toString().lowercase().replaceFirstChar { it.uppercase() }} account")
            i += 1
        }
        print("Choose an option 1,2 or 3: ")
        userChoice = readln().toIntOrNull()
    }
    return userChoice
}