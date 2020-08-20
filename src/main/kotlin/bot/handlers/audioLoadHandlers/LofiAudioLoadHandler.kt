package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder

class LofiAudioLoadHandler(private val event: CommandEvent) : QuietAudioLoadHandler(event) {
    override fun trackLoaded(track: AudioTrack) {
        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription("Good choice")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.reply(builder.build())

            audioPlayerManager.queueTrack(event.guild, track)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
