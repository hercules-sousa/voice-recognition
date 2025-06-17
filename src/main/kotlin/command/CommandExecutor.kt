package command

interface CommandExecutor {
    fun execute(command: String)
}