package command.impl

import command.CommandExecutor
import org.example.command.impl.runCommand

class ChromeExecutor : CommandExecutor {
    override fun execute(command: String) {
        runCommand("google-chrome")
    }
}