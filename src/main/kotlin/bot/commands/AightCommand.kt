package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.time.Instant

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

        builder.setDescription("Imma head out")
        builder.setFooter(event.author.name)
        builder.setTimestamp(Instant.now())

        event.guild.audioManager.closeAudioConnection()

        event.reply(builder.build())
    }
}