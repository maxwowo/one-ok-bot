package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder

class GimmeAudioLoadHandler(private val event: CommandEvent) : QuietAudioLoadHandler(event) {
    override fun trackLoaded(track: AudioTrack) {
        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription("**MOOOOOOOORE**")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            musicManager.scheduler.queue(track)

            event.reply(builder.build())
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
