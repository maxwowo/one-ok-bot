package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import net.dv8tion.jda.api.EmbedBuilder

class RockAudioLoadHandler(private val event: CommandEvent) : QuietAudioLoadHandler(event) {
    override fun playlistLoaded(playlist: AudioPlaylist) {
        val tracks = playlist.tracks

        /* Shuffle the tracks so we don't play through the same playlist every time */
        tracks.shuffle()

        val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()
        val firstTrackIndex = playlist.tracks.indexOf(firstTrack)
        val remainingTracks = tracks.subList(firstTrackIndex + 1, tracks.size) + tracks.subList(0, firstTrackIndex)

        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription("**YEAHHHH LET'S GOOOOOOOO**")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.reply(builder.build())

            musicManager.scheduler.queue(firstTrack, remainingTracks)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
