package bot.listeners

import bot.sounds.audioPlayerManager
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class VoiceLeaveListener : ListenerAdapter() {
    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent) {
        /* If the bot is the one that left */
        if (event.member.user == event.jda.selfUser) {
            val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

            /* Reset the scheduler */
            musicManager.scheduler.looping = false
            musicManager.scheduler.clear()
        } else if (event.channelLeft.members.size == 1) {
            event.guild.audioManager.closeAudioConnection()
        }
    }
}
