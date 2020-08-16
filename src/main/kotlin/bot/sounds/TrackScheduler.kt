package bot.sounds

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class TrackScheduler(private val player: AudioPlayer) : AudioEventAdapter() {
    private val queue: BlockingQueue<AudioTrack>

    init {
        queue = LinkedBlockingQueue()
    }

    fun queue(track: AudioTrack) {
        if (!player.startTrack(track, true)) {
            queue.add(track)
        }
    }

    fun queue(tracks: List<AudioTrack>) {
        queue.addAll(if (player.startTrack(tracks.first(), true)) tracks.drop(1) else tracks)
    }

    fun nextTrack() {
        player.startTrack(queue.poll(), false)
    }

    fun clear() {
        queue.clear()
        nextTrack()
    }

    override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, reason: AudioTrackEndReason) {
        if (reason.mayStartNext) {
            nextTrack()
        }
    }
}
