package bot

import bot.exceptions.EnvironmentVariableNotFoundException

object Configuration {
    val TOKEN = System.getenv("TOKEN") ?: throw EnvironmentVariableNotFoundException("TOKEN")
    val PREFIX = System.getenv("PREFIX") ?: throw EnvironmentVariableNotFoundException("PREFIX")
}
