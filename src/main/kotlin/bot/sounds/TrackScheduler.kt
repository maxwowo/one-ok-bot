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

    fun queue(firstTrack: AudioTrack, remainingTracks: List<AudioTrack>) {
        if (!player.startTrack(firstTrack, true)) {
            queue.add(firstTrack)
        }

        queue.addAll(remainingTracks)
    }

    fun nextTrack() {
        player.startTrack(queue.poll(), false)
    }

    fun shuffle() {
        val shuffled = queue.shuffled()

        queue.clear()
        queue.addAll(shuffled)
    }

    fun isEmpty(): Boolean {
        return queue.isEmpty() && player.playingTrack == null
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
