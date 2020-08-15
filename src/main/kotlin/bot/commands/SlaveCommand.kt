package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

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
        val builder = EmbedBuilder()
        val bots = ArrayList<String>()

        for (member in event.guild.members) {
            val user = member.user

            if (user.isBot && user != event.jda.selfUser) {
                bots.add(user.asMention)
            }
        }

        if (bots.isEmpty()) {
            builder.setDescription("No slaves to be found")
        } else {
            builder.setDescription(bots.joinToString(" "))
        }

        event.reply(builder.build())
    }
}
