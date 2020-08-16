package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents !slave command
 *
 * Tags other bots if present
 */
class SlaveCommand : Command() {
    init {
        name = "slave"
        help = "Tags other bots if present"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()
        val slaves = setOf("185306839880040448", "184506248589213696")

        val mentions = event.guild.members
            .filter { it.user != event.jda.selfUser && (it.user.isBot || it.user.id in slaves) }
            .sortedBy { it.user.isBot }
            .map { it.user.asMention }

        if (mentions.isEmpty()) {
            builder.setDescription("No slaves to be found")
        } else {
            builder.setDescription(mentions.joinToString(" "))
        }

        event.reply(builder.build())
    }
}
