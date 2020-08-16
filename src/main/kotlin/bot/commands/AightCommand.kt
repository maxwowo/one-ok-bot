package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !aight command
 *
 * Leaves the voice channel
 */
class AightCommand : Command() {
    init {
        name = "aight"
        help = "Leaves the voice channel"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        event.guild.audioManager.closeAudioConnection()

        builder.setDescription("Imma head out")

        event.reply(builder.build())
    }
}
