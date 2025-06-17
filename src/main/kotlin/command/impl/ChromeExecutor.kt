package command.impl

import command.CommandExecutor
import org.example.runCommand

class ChromeExecutor : CommandExecutor {
    override fun execute(command: String) {
        runCommand("google-chrome")
    }
}