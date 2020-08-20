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
        category = Category("Music")
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        if (event.guild.audioManager.isConnected) {
            builder.setDescription("Imma head out")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.guild.audioManager.closeAudioConnection()
        } else {
            builder.setDescription("${event.author.asMention} Bro I'm not even in a voice channel")
        }

        event.reply(builder.build())
    }
}
