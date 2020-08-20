package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !skincare command
 *
 * Replies with Patrick Bateman's skincare routine
 */
class SkincareCommand : Command() {
    init {
        name = "skincare"
        help = "Offers you some helpful skincare tips"
        category = Category("Random")
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription(javaClass.getResource("/copypastas/patrick_bateman.txt").readText())

        event.reply(builder.build())
    }
}
