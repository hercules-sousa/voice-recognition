package org.example.command.impl

import org.example.command.CommandExecutor

class ChromeExecutor : CommandExecutor {
    override fun execute(command: String) {
        runCommand("google-chrome")
    }
}