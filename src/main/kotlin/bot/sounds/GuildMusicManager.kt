package bot.sounds

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

class GuildMusicManager(manager: AudioPlayerManager) {
    val player: AudioPlayer = manager.createPlayer()
    val scheduler: TrackScheduler

    init {
        scheduler = TrackScheduler(player)
        player.addListener(scheduler)
    }

    fun getSendHandler(): AudioPlayerSendHandler {
        return AudioPlayerSendHandler(player)
    }
}
