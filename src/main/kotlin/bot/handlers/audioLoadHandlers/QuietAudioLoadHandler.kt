package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

open class QuietAudioLoadHandler(private val event: CommandEvent) : AudioLoadHandler(event) {
    override fun trackLoaded(track: AudioTrack) {
        try {
            connectToVoiceChannel()

            audioPlayerManager.queueTrack(event.guild, track)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        val tracks = playlist.tracks
        val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()
        val firstTrackIndex = playlist.tracks.indexOf(firstTrack)
        val remainingTracks = tracks.subList(firstTrackIndex + 1, tracks.size) + tracks.subList(0, firstTrackIndex)

        try {
            connectToVoiceChannel()

            audioPlayerManager.queueTracks(event.guild, firstTrack, remainingTracks)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
