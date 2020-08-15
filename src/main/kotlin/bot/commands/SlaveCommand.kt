package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents !slave command
 *
 * Tags other music bots if present
 */
class SlaveCommand : Command() {
    init {
        name = "slave"
        help = "Tags other music bots if present"
    }

    override fun execute(event: CommandEvent) {
        val bots = ArrayList<String>()

        for (member in event.guild.members) {
            val user = member.user

            if (user.isBot && user != event.jda.selfUser) {
                bots.add(member.id)
            }
        }

        if (bots.isEmpty()) {
            event.reply("No slaves to be found")
        } else {
            event.reply(bots.joinToString(" ") { "<@$it>" })
        }
    }
}
