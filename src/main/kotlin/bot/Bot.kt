package bot

import bot.commands.BruhCommand
import bot.commands.PlayCommand
import bot.configurations.configuration
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import net.dv8tion.jda.api.JDABuilder

/**
 * Class that represents the bot
 *
 * Initializes the various JDA factories and runs the bot
 */
class Bot {
    fun start() {
        val builder = JDABuilder.createDefault(configuration.token)
        val waiter = EventWaiter()
        val client = CommandClientBuilder()

        /* Client setup */
        with(client) {
            useDefaultGame()
            setOwnerId(configuration.owner)
            setPrefix(configuration.prefix)

            addCommands(
                /* Command to get the bot to reply "Bruh" back to you */
                BruhCommand(),
                /* Command to play a song */
                PlayCommand()
            )
        }

        /* Builder setup */
        with(builder) {
            addEventListeners(waiter, client.build())
            build()
        }
    }
}
