package bot.commands

import bot.configurations.configurations
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !lofi command
 *
 * Clears the queue and plays No Scared
 */
class MoreCommand : Command() {
    init {
        name = "more"
        help = "GIVE ME MOOOORE"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("GIVE ME MOOOORE")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        audioPlayerManager.clearQueue(event.guild)
        audioPlayerManager.loadAndPlay(event, configurations.noScaredURL)

        event.reply(builder.build())
    }
}
