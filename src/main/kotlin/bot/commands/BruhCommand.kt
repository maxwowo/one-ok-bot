package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

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
        event.reply("Bruh")
    }
}
