package bot.handlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder

class LofiAudioLoadHandler(private val event: CommandEvent) : QuietAudioLoadHandler(event) {
    override fun trackLoaded(track: AudioTrack) {
        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription("Good taste")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.reply(builder.build())

            musicManager.scheduler.queue(track)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
