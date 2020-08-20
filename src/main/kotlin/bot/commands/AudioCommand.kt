package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

abstract class AudioCommand : Command() {
    abstract fun handleQueueEmpty(event: CommandEvent)
    abstract fun handleCommand(event: CommandEvent)

    override fun execute(event: CommandEvent) {
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)
        val scheduler = musicManager.scheduler

        if (event.guild.audioManager.isConnected) {
            if (scheduler.isEmpty()) {
                handleQueueEmpty(event)
            } else {
                handleCommand(event)
            }
        } else {
            val builder = EmbedBuilder()

            builder.setDescription("${event.author.asMention} Bro I'm not even in a voice channel")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }
    }
}
