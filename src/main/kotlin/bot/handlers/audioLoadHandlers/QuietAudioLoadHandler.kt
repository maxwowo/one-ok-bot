package bot.handlers.audioLoadHandlers

import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class QuietAudioLoadHandler(event: CommandEvent) : AudioLoadHandler(event) {
    override fun trackLoadedReply(track: AudioTrack) {}
    override fun playlistLoadedReply(playlist: AudioPlaylist) {}
}
