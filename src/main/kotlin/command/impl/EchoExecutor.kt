package org.example.command.impl

import org.example.command.CommandExecutor

class EchoExecutor : CommandExecutor {
    override fun execute(command: String) {
        runCommand("sh -c 'echo \$PATH'")
    }
}