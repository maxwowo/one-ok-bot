package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents !bruh command
 *
 * Replies "Bruh" back
 */
class BruhCommand : Command() {
    init {
        name = "bruh"
        help = "Greets you"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("__***bruh***__")

        event.reply(builder.build())
    }
}
