package bot.commands

import bot.configurations.configurations
import bot.handlers.audioLoadHandlers.QuietAudioLoadHandler
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !gimme command
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
        val guild = event.guild

        builder.setDescription("**MOOOOOOOORE**")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        audioPlayerManager.clearQueue(guild)
        audioPlayerManager.loadAndPlay(guild, configurations.noScaredURL, QuietAudioLoadHandler(event))

        event.reply(builder.build())
    }
}
