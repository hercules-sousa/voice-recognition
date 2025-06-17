package command.impl

import command.CommandExecutor
import org.example.runCommand

class ContainerExecutor(private val action: String) : CommandExecutor {
    override fun execute(command: String) {
        runCommand("synchro-utils dfe $action-containers")
    }
}