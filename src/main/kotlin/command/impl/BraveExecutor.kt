package org.example.command.impl

import org.example.command.CommandExecutor

class BraveExecutor(private val url: String) : CommandExecutor {
    override fun execute(command: String) {
        runCommand("brave-browser --url $url")
    }
}