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

            /* Filter by whether the user is a bot or a slave by default */
            .filter { it.user != event.jda.selfUser && (it.user.isBot || it.user.id in slaves) }

            /* Order by whether user is a bot */
            .sortedBy { it.user.isBot }

            /* Map each user to their mention */
            .map { it.user.asMention }

        if (mentions.isEmpty()) {
            builder.setDescription("No slaves to be found")
        } else {
            builder.setDescription(mentions.joinToString(" "))
        }

        event.reply(builder.build())
    }
}
