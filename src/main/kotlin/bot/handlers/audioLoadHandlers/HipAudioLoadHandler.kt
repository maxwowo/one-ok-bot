package bot.handlers.audioLoadHandlers

import bot.configurations.configurations
import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder

class HipAudioLoadHandler(private val event: CommandEvent) : QuietAudioLoadHandler(event) {
    override fun trackLoaded(track: AudioTrack) {
        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription(javaClass.getResource("/copypastas/hip_to_be_square.md").readText())
            builder.setImage(configurations.batemanAxeURL)

            musicManager.scheduler.queue(track)

            event.reply(builder.build())
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
