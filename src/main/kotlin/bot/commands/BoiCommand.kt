package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !boi command
 *
 * Fastest boi in the west
 */
class BoiCommand : Command() {
    init {
        name = "boi"
        help = "Fastest boi in the west"
        category = Category("Random")
    }

    override fun execute(event: CommandEvent) {
        val message = event.message

        message.addReaction("\uD83C\uDDE7").queue()
        message.addReaction("\uD83C\uDDF4").queue()
        message.addReaction("\uD83C\uDDEE").queue()
    }
}
