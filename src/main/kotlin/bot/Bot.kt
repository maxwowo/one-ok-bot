package bot

import bot.commands.BruhCommand
import bot.configurations.configuration
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import net.dv8tion.jda.api.JDABuilder

class Bot {
    fun start() {
        val builder = JDABuilder.createDefault(configuration.token)
        val waiter = EventWaiter()
        val client = CommandClientBuilder()

        /* Client setup */
        client.setOwnerId(configuration.owner)
        client.setPrefix(configuration.prefix)
        client.useDefaultGame()

        client.addCommands(
            BruhCommand()
        )

        /* Builder setup */
        builder.addEventListeners(waiter, client.build())
        builder.build()
    }
}
