package command.impl

import command.CommandExecutor
import org.example.command.impl.runCommand

class BraveExecutor(private val url: String) : CommandExecutor {
    override fun execute(command: String) {
        runCommand("brave-browser --url $url")
    }
}