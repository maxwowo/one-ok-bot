package bot.listeners

import bot.sounds.audioPlayerManager
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class VoiceLeaveListener : ListenerAdapter() {
    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent) {
        if (event.member.user == event.jda.selfUser) {
            val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

            musicManager.scheduler.clear()
        } else if (event.channelLeft.members.size == 1) {
            event.guild.audioManager.closeAudioConnection()
        }
    }
}
