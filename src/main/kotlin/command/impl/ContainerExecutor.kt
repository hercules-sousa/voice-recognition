package org.example.command.impl

import org.example.command.CommandExecutor

class ContainerExecutor(private val action: String) : CommandExecutor {
    override fun execute(command: String) {
        runCommand("synchro-utils dfe $action-containers")
    }
}