package bot

import bot.commands.BruhCommand
import bot.commands.OkCommand
import bot.commands.OneCommand
import bot.commands.SlaveCommand
import bot.configurations.configuration
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter

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
            setOwnerId(configuration.owner)
            setPrefix(configuration.prefix)

            addCommands(
                /* Command to get the bot to reply "Bruh" back to you */
                BruhCommand(),

                /* Command to play a song */
                OneCommand(),

                /* Command to skip a song */
                OkCommand(),

                /* Tags other music bots if present */
                SlaveCommand()
            )
        }

        /* Builder setup */
        with(builder) {
            addEventListeners(waiter, client.build())
            setChunkingFilter(ChunkingFilter.ALL)
            enableIntents(GatewayIntent.GUILD_MEMBERS)
            build()
        }
    }
}
