package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

class BruhCommand : Command() {
    init {
        name = "bruh"
        help = "Says bruh to you"
    }

    override fun execute(event: CommandEvent) {
        event.reply("Bruh")
    }
}
