package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !rewind command
 *
 * Loops the current track
 */
class RewindCommand : Command() {
    init {
        name = "rewind"
        help = "Loops the current track"
        category = Category("Music")
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        // If scheduler  is currently looping
        if (musicManager.scheduler.looping) {
            builder.setDescription("Ahh that's hot")
        } else {
            builder.setDescription("It's rewind time")
        }

        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        // Toggle scheduler looping
        musicManager.scheduler.looping = !musicManager.scheduler.looping

        event.reply(builder.build())
    }
}
