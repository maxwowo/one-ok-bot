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
class GimmeCommand : Command() {
    init {
        name = "gimme"
        help = "GIVE ME MOOOOOOOORE"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("**MOOOOOOOORE**")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        audioPlayerManager.clearQueue(event.guild)
        audioPlayerManager.loadAndPlay(event, configurations.noScaredURL)

        event.reply(builder.build())
    }
}
