package bot

import bot.enums.Environments
import bot.enums.Variables
import mu.KotlinLogging

fun main() {
    val logger = KotlinLogging.logger {}
    val environment = System.getenv().getOrDefault(Variables.ENVIRONMENT.value, Environments.DEVELOPMENT.value)
}
