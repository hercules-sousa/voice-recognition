package command.impl

import command.CommandExecutor
import org.example.command.impl.runCommand

class EchoExecutor : CommandExecutor {
    override fun execute(command: String) {
        runCommand("echo \$PATH")
    }
}